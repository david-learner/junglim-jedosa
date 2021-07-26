function goBack() {
    window.history.back();
}

/**
 * 게시글 등록
 */
function saveArticle() {
    let boardName = document.getElementById('board-name').value;
    let boardId = document.getElementById('board-id').value;
    let isNotice = document.getElementById('is-notice').checked;
    let boardListUrl = '/board/' + boardName

    let title = document.getElementById("title").value;
    let content = $('#article-body').summernote('code');

    console.log("title: " + title);
    console.log("content: " + content);
    console.log("isNotice: " + isNotice);


    let boardFormData = new FormData();
    // article common form data
    boardFormData.append("boardId", boardId);
    boardFormData.append("isNotice", isNotice);
    boardFormData.append("title", title);
    boardFormData.append("content", content);
    // boardFormData = {
    //     boardId: boardId,
    //     title: title,
    //     content: content
    // }
    if (boardId === "2") {
        let thumbnail = document.getElementById("thumbnail-preview").getAttribute("src");
        console.log("thumbnail: " + thumbnail);
        boardFormData.append("thumbnail", thumbnail);
    }

    console.log(boardFormData);

    $.ajax({
        url: boardListUrl,
        type: 'POST',
        data: boardFormData,
        processData: false,
        contentType: false,
        // contentType: "application/x-www-form-urlencoded"
    }).done(function (data, textStatus, jqXHR) {
        alert("작성하신 글이 등록되었습니다");
        window.location.href = boardListUrl;
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

function editArticleForm() {
    // 수정폼 생성
    generateEditor(document.getElementById('article-body'));
    // toggle
    toggleTitle();
    toggleArticleController();
    toggleDisplay(document.getElementById("comment-area"));
}

function showArticle() {
    toggleTitle();
    toggleArticleController();
    toggleDisplay();
}

function toggleTitle() {
    let title = document.getElementById("article-title");
    let titleEditForm = document.getElementById("article-title-edit-form");

    if (title.classList.contains('d-none')) {
        title.classList.remove('d-none');
    } else {
        title.classList.add('d-none');
    }

    if (titleEditForm.classList.contains('d-none')) {
        titleEditForm.classList.remove('d-none');
    } else {
        titleEditForm.classList.add('d-none');
    }
}

function toggleArticleController() {
    let viewController = document.getElementById('article-view-controller');
    let editController = document.getElementById('article-edit-controller');

    if (viewController.classList.contains('d-none')) {
        viewController.classList.remove('d-none');
    } else {
        viewController.classList.add('d-none');
    }

    if (editController.classList.contains('d-none')) {
        editController.classList.remove('d-none');
    } else {
        editController.classList.add('d-none');
    }
}

function toggleDisplay(element) {
    if (element.classList.contains('d-none')) {
        element.classList.remove('d-none');
    } else {
        element.classList.add('d-none');
    }
}

function editArticle() {
    let boardName = document.getElementById('board-name').value;
    let articleId = document.getElementById('article-id').innerHTML;
    let boardListUrl = '/board/' + boardName
    let editUrl = boardListUrl + '/' + articleId;

    // form data
    // let memberId = document.getElementById("member-id").value;
    let isNotice = document.getElementById("is-notice").value;
    let replyStatus = document.getElementById("reply-status").value;
    let password = document.getElementById("password").value;
    let thumbnail = document.getElementById("thumbnail").value;

    let title = document.getElementById("article-title-edit-form").value;
    let content = $('#article-body').summernote('code');

    let boardFormData = {
        // memberId: memberId,
        isNotice: isNotice,
        replyStatus: replyStatus,
        password: password,
        title: title,
        content: content,
        thumbnail: thumbnail
    }

    $.ajax({
        url: editUrl,
        type: 'PATCH',
        data: boardFormData,
        contentType: "application/x-www-form-urlencoded"
    }).done(function (data, textStatus, jqXHR) {
        alert("수정되었습니다");
        location.reload();
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

function deleteArticle() {
    if (!confirm("게시글을 정말 삭제하시겠습니까?")) {
        return;
    }

    let boardName = document.getElementById('board-name').value;
    let articleId = document.getElementById('article-id').innerHTML;
    let boardListUrl = '/board/' + boardName
    let deleteUrl = boardListUrl + '/' + articleId;

    $.ajax({
        url: deleteUrl,
        type: 'DELETE',
    }).done(function (data, textStatus, jqXHR) {
        alert("삭제되었습니다");
        window.location.href = boardListUrl;
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

function saveComment() {
    let boardName = document.getElementById('board-name').value;
    let articleId = document.getElementById('article-id').innerHTML;
    let boardListUrl = '/board/' + boardName
    let saveCommentUrl = boardListUrl + '/' + articleId + '/comments';

    let content = $('#comment-editor').summernote('code');

    let boardFormData = {
        content: content
    }

    $.ajax({
        url: saveCommentUrl,
        type: 'POST',
        data: boardFormData,
        contentType: "application/x-www-form-urlencoded"
    }).done(function (data, textStatus, jqXHR) {
        alert('답변이 등록되었습니다');
        location.reload();
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

function editCommentForm(event) {
    let viewController = event.parentElement;
    let editController = viewController.nextElementSibling;
    let commentEditor = viewController.parentElement.previousElementSibling;
    generateEditor(commentEditor);
    toggleDisplay(viewController);
    toggleDisplay(editController);
}

function showComment(event) {
    let editController = event.parentElement;
    let viewController = editController.previousElementSibling;
    // editor를 생성하면 생성을 지정한 엘리먼트 형제로 하나가 더 생긴다.
    // 그래서 prevSibling을 2번하여 생성을 지정했던 엘리먼트를 찾아가서 destroy해준다
    let editor = editController.parentElement.previousElementSibling.previousElementSibling;
    removeEditor(editor);
    toggleDisplay(viewController);
    toggleDisplay(editController);
}

function editComment(event) {
    let boardName = document.getElementById('board-name').value;
    let articleId = document.getElementById('article-id').innerHTML;
    let commentId = event.parentElement.parentElement.parentElement
        .children.namedItem("comment-info")
        .children.namedItem("comment-id").value;
    let boardListUrl = '/board/' + boardName
    let updateCommentUrl = boardListUrl + '/' + articleId + '/comments/' + commentId;

    let editor = event.parentElement.parentElement.parentElement
        .children.namedItem("comment-content");
    let content = $(editor).summernote('code');

    let commentFormData = {
        content: content
    }

    $.ajax({
        url: updateCommentUrl,
        type: 'PATCH',
        data: commentFormData,
        contentType: "application/x-www-form-urlencoded"
    }).done(function (data, textStatus, jqXHR) {
        alert('수정되었습니다');
        location.reload();
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

function deleteComment(event) {
    let boardName = document.getElementById('board-name').value;
    let articleId = document.getElementById('article-id').innerHTML;
    // closest 탐색시 부모만 된다. 부모의 형제는 탐색 X
    let commentId = event.closest("div[name='comment']")
        .children.namedItem("comment-info")
        .children.namedItem("comment-id").value;

    let boardListUrl = '/board/' + boardName
    let deleteCommentUrl = boardListUrl + '/' + articleId + '/comments/' + commentId;

    $.ajax({
        url: deleteCommentUrl,
        type: 'DELETE',
    }).done(function (data, textStatus, jqXHR) {
        alert("삭제되었습니다");
        location.reload();
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

function removeEditor(element) {
    $(element).summernote('destroy');
}

function generateEditor(targetElementSelector) {
    $(targetElementSelector).summernote({
        focus: true,
        height: 500,
        callbacks: {
            onImageUpload: function (files) {
                // upload image to server and create imgNode...
                console.log(files);

                let data = new FormData()
                data.append("file", files[0]);

                $.ajax({
                    type: 'POST',
                    enctype: 'multipart/form-data',
                    url: '/files/upload',
                    data: data,
                    processData: false,
                    contentType: false,
                    cache: false
                }).done(function (data, textStatus, jqXHR) {
                    $(targetElementSelector).summernote('insertImage', data.path, 'image');
                });
            }
        }
    });
}

function updateThumbnail(event) {
    event.nextElementSibling.textContent = event.files[0].name;
    let preview = document.getElementById("thumbnail-preview");

    // post로 썸네일 업로드
    let data = new FormData();
    data.append("file", event.files[0]);

    $.ajax({
        type: 'POST',
        enctype: 'multipart/form-data',
        url: '/files/upload',
        data: data,
        processData: false,
        contentType: false,
        cache: false
    }).done(function (data, textStatus, jqXHR) {
        console.log(data.path);
        let absolutePath = data.path;
        preview.setAttribute("src", absolutePath);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseText);
    });
}

function deleteThumbnail() {
    let thumbnailFile = document.getElementById("thumbnail-file");
    let thumbnailFileParent = thumbnailFile.parentElement;
    let thumbnailFileName = thumbnailFile.nextElementSibling;

    // 썸네일 삭제
    let thumbnailPreview = document.getElementById("thumbnail-preview");
    thumbnailPreview.setAttribute("src", "");
    thumbnailFileName.textContent = "";

    // 폼에서 썸네일 파일 정보 삭제
    // 임시폼 생성, 임시폼에 썸네일 파일 엘리먼트 추가, 임시폼 리셋
    let tempForm = document.createElement("form");
    tempForm.appendChild(thumbnailFile)
    tempForm.reset();

    // 썸네일 파일 엘리먼트 복구
    thumbnailFileParent.insertBefore(thumbnailFile, thumbnailFileName);
}