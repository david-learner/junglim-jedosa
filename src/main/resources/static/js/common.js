function openMenuPageEditForm() {
    $('#article-body').summernote({
        focus: true,
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
                    console.log('\\' + data.path);
                    $('#article-body').summernote('insertImage', '\\' + data.path, 'image777');
                });
                // $summernote.summernote('insertNode', imgNode);
            }
        }
    });
    toggleMenuController();
}

function toggleMenuController() {
    let editBtn = document.getElementById('content-edit-btn');
    // editBtn.classList.add('d-none');
    let saveBtn = document.getElementById('content-save-btn');
    // saveBtn.classList.remove('d-none');
    let cancelBtn = document.getElementById('content-cancel-btn');
    // cancelBtn.classList.remove('d-none');

    if (editBtn.classList.contains('d-none')) {
        editBtn.classList.remove('d-none');
    } else {
        editBtn.classList.add('d-none');
    }

    if (saveBtn.classList.contains('d-none')) {
        saveBtn.classList.remove('d-none');
    } else {
        saveBtn.classList.add('d-none');
    }

    if (cancelBtn.classList.contains('d-none')) {
        cancelBtn.classList.remove('d-none');
    } else {
        cancelBtn.classList.add('d-none');
    }
}

function updateMenuPage() {

    let id = document.getElementById("one-page-menu-id").value;
    let content = $('#article-body').summernote('code');

    console.log('id:' + id + ' content: ' + content);

    let menuPageFormData = {
        id: id,
        content: content
    }

    $.ajax({
        url: "/menu/order-process",
        type: 'PATCH',
        data: menuPageFormData,
        contentType: "application/x-www-form-urlencoded"
    }).done(function (data, textStatus, jqXHR) {
        alert("수정되었습니다")
        $('#article-body').summernote('destroy');
        toggleMenuController();
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseText);
    });
}

function returnMenuPage() {
    $('#article-body').summernote('destroy');
    toggleMenuController();
}

function popup(selectedElement, windowName) {
    let url = selectedElement.getAttribute("href");
    window.open(url, windowName, 'width=1100,height=700,scrollbars=yes');
    return false;
}