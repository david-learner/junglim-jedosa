window.onload = function () {
    // 간지 옵션 미노출
    document.querySelector("#flyleaf-option-form").style.display = "none";
    document.querySelector("#flyleaf-content-printing-option-form").style.display = "none";
}

function viewImageFromList(event) {
    let itemImageViewer = document.getElementById("item-image-viewer");
    let itemImageViewrImage = itemImageViewer.firstElementChild;
    let itemImageSource = event.getAttribute("src");
    itemImageViewrImage.setAttribute("src", itemImageSource);
}

// 간지 추가 옵션
function toggleFlyleafOption() {
    let flyleafOptionValue = document.querySelector("input[name=flyleaf]:checked").value;
    if (flyleafOptionValue === 'true') {
        document.querySelector("#flyleaf-option-form").style.display = "";
    } else {
        document.querySelector("#flyleaf-option-form").style.display = "none";
    }
}

// 간지 문구 인쇄 옵션
function toggleFlyleafContentPrintingOption() {
    let flyleafContentPrintingOptionValue = document.querySelector("input[name=flyleaf-content-printing]:checked").value;
    if (flyleafContentPrintingOptionValue === 'true') {
        document.querySelector("#flyleaf-content-printing-option-form").style.display = "";
    } else {
        document.querySelector("#flyleaf-content-printing-option-form").style.display = "none";
    }
}