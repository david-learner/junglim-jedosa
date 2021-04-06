function updateProductSampleImage(clickedElement) {
    let fileElement = clickedElement.parentElement.querySelector("input");

    let fileName = fileElement.files[0].name;
    console.log("filename: " + fileName);
    if (/(\.png|\.jpg|\.jpeg)$/i.test(fileName) === false) {
        alert("png, jpg, jpeg 파일 형식만 지원합니다");
        fileElement.files[0] = undefined;
        return;
    }

    let sampleImageSequence = fileElement.getAttribute("data-product-image-sequence");
    let imageFileFormData = new FormData();
    imageFileFormData.append("file", fileElement.files[0]);
    uploadFile(imageFileFormData, function updateSampleImagePath(path) {
        let sampleImageFormData = new FormData();
        sampleImageFormData.append("sequence", sampleImageSequence);
        sampleImageFormData.append("path", path);
        requestUpdateProductSampleImage(sampleImageFormData);
        let preview = clickedElement.parentElement.querySelector("div[name='product-sample-image-preview'] img");
        preview.setAttribute("src", path);
    });
}

function uploadFile(data, updatePath) {
    $.ajax({
        type: 'POST',
        enctype: 'multipart/form-data',
        url: '/files/upload',
        data: data,
        processData: false,
        contentType: false,
        cache: false
    }).done(function (data, textStatus, jqXHR) {
        let uploadedPath = '\\' + data.path; // return absolutePath
        updatePath(uploadedPath);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseText);
    });
}

function requestUpdateProductSampleImage(data) {
    $.ajax({
        type: 'POST',
        enctype: 'multipart/form-data',
        url: '/admin/menus/main/product-sample-images',
        data: data,
        processData: false,
        contentType: false,
        cache: false
    }).done(function (data, textStatus, jqXHR) {
        alert("저장 되었습니다");
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseText);
    });
}

function deleteProductSampleImage(clickedElement) {
    let preview = clickedElement.parentElement.querySelector("div[name='product-sample-image-preview'] img");
    let fileElement = clickedElement.parentElement.querySelector("input");
    let sequence = fileElement.getAttribute("data-product-image-sequence");
    requestDeleteProductSampleImage(sequence);
    fileElement.value = "";
    preview.setAttribute("src", "");
}

function requestDeleteProductSampleImage(productSampleImageSequence) {
    $.ajax({
        url: '/admin/menus/main/product-sample-images/' + productSampleImageSequence,
        type: 'DELETE',
    }).done(function (data, textStatus, jqXHR) {
        alert("삭제되었습니다");
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}