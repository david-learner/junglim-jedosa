var paperPriceTable = {};

window.onload = function () {
    updatePrice();
}

// DOMContentLoaded는 HTML만을 완전히 불러왔을 때 실행된다
window.addEventListener('DOMContentLoaded', (event) => {
    hideFlyleafItemOption();
    validateEmptyOption();
    getPaperPriceTableFromServer();
})

function getPaperPriceTableFromServer() {

    $.ajax({
        url: '/api/paper-prices',
        method: 'GET',
        dataType: "json",
    }).done(function (data, textStatus, jqXHR) {
        paperPriceTable = data;
        console.log('paperPriceTable');
        console.log(paperPriceTable);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

// 주문에 필요한 옵션들이 존재하는지
function validateEmptyOption() {
    if(!hasOverOneOption()) {
        disableOrderButton();
    }
    if (hasOverOneOption()) {
        enableOrderButton();
    }
}

function hasOverOneOption() {
    if(document.getElementById("paper-size").length === 0 ||
        document.getElementById("binding-type").length === 0 ||
        document.getElementById("cover-printing-type").length === 0 ||
        document.getElementById("cover-printing-color-type").length === 0 ||
        document.getElementById("cover-paper-type").length === 0 ||
        document.getElementById("cover-coating-type").length === 0 ||
        document.getElementById("cover-design-name").length === 0 ||
        document.getElementById("content-printing-type").length === 0 ||
        document.getElementById("content-printing-color-type").length === 0 ||
        document.getElementById("content-paper-type").length === 0 ||
        document.getElementById("flyleaf-color-type").length === 0) {
        return false;
    }
    return true;
}

// disabled 속성을 넣으면 onClick이 먹지 않기 때문에 제외
function disableOrderButton() {
    let orderButton = document.getElementById("order-button");
    orderButton.setAttribute("onClick", "errorOfNotEnoughItemOption()");
}

function enableOrderButton() {
    let orderButton = document.getElementById("order-button");
    orderButton.setAttribute("onClick", "addToCart()");
}
// todo 주문버튼 막는 동작, 복구 동작 확인
function errorOfNotEnoughItemOption() {
    // 관리자 - 상품 옵션 항목에서 상품 옵션을 추가해야 합니다.
    alert("현재 주문할 수 없는 상태입니다.\n관리자에서 문의하시기 바랍니다.\n(오류내용: 상품 옵션 부족)");
}

function hideFlyleafItemOption() {
    let flyleafOptionForm = document.querySelector("#flyleaf-option-form");
    if (flyleafOptionForm != null) {
        flyleafOptionForm.style.display = "none";
    }

    let flyleafContentPrintingOptionForm = document.querySelector("#flyleaf-content-printing-option-form");
    if (flyleafContentPrintingOptionForm != null) {
        flyleafContentPrintingOptionForm.style.display = "none";
    }
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
    // 간지 여부에 따른 페이지 수 변경
    updatePageCountPerBook();
}

// 간지 문구 인쇄 옵션
function toggleFlyleafContentPrintingOption() {
    let flyleafContentPrintingOptionValue = document.querySelector("input[name=flyleaf-content-printing]:checked").value;
    if (flyleafContentPrintingOptionValue === 'true') {
        document.querySelector("#flyleaf-content-printing-option-form").style.display = "";
    } else {
        document.querySelector("#flyleaf-content-printing-option-form").style.display = "none";
    }

    updatePrice();
}

function updatePageCountPerBook() {
    // 본문 페이지 수
    let contentPageCount = parseInt(document.getElementById("content-page-count").value);
    // 간지 수
    let flyleafCountPerBook = parseInt(document.getElementById("flyleaf-count-per-book").value);
    // 간지 선택 여부
    let hasFlyleaf = document.querySelector("input[name='flyleaf']:checked").value;
    let sumForPageCountPerBook = 0;
    // 만약 간지 추가가 아니라면 간지 수는 제외
    if (hasFlyleaf === "true") {
        sumForPageCountPerBook = contentPageCount + flyleafCountPerBook;
    }
    if (hasFlyleaf === 'false') {
        sumForPageCountPerBook = contentPageCount;
    }
    let pageCountPerBook = document.getElementById("page-count-per-book");
    pageCountPerBook.value = sumForPageCountPerBook;

    updatePrice();
}


function addToCart() {
    let ordererId = document.getElementById("orderer-id").value;
    let itemId = document.getElementById("item-id").value;
    let itemName = document.getElementById("item-name").value;
    // 종이 사이즈
    let paperSize = document.getElementById("paper-size").value;
    // 제본
    let bindingType = document.getElementById("binding-type").value;
    let bindingDirection = document.getElementById("binding-direction").value;
    // 표지
    let coverPrintingType = document.getElementById("cover-printing-type").value;
    let coverPrintingColorType = document.getElementById("cover-printing-color-type").value;
    let coverPaperType = document.getElementById("cover-paper-type").value;
    let coverCoatingType = document.getElementById("cover-coating-type").value;
    let coverDesignName = document.getElementById("cover-design-name").value;
    // 본문
    let contentPrintingType = document.getElementById("content-printing-type").value;
    let contentPrintingColorType = document.getElementById("content-printing-color-type").value;
    let contentPageCount = document.getElementById("content-page-count").value;
    let contentPaperType = document.getElementById("content-paper-type").value;
    // 간지
    let flyleaf = document.querySelector("input[name='flyleaf']:checked").value;
    let flyleafColorType = document.getElementById("flyleaf-color-type").value;
    let flyleafCountPerBook = document.getElementById("flyleaf-count-per-book").value;
    let flyleafContentPrinting = document.querySelector("input[name='flyleaf-content-printing']:checked").value;
    let flyleafContentPrintingValue = document.getElementById("flyleaf-content-printing-value").value;
    let flyleafInsertLocation = document.getElementById("flyleaf-insert-location").value;
    // 고객 요청사항
    let customerComment = document.getElementById("customer-comment").value;
    // 제작
    let pageCountPerBook = document.getElementById("page-count-per-book").value;
    let bookCount = document.getElementById("book-count").value;
    let itemPrice = document.getElementById("item-price").value;
    let vat = document.getElementById("vat").value;
    let totalPrice = document.getElementById("total-price").value;

    // console.log("ordererId: " + ordererId)
    // console.log("itemId: " + itemId)
    // console.log("itemName: " + itemName)
    // console.log("bindingType: " + bindingType)
    // console.log("bindingDirection: " + bindingDirection)
    // console.log("coverPrintingType: " + coverPrintingType)
    // console.log("coverPrintingColorType: " + coverPrintingColorType)
    // console.log("coverPaperType: " + coverPaperType)
    // console.log("coverCoatingType: " + coverCoatingType)
    // console.log("coverDesignType: " + coverDesignType)
    // console.log("contentPrintingType: " + contentPrintingType)
    // console.log("contentPrintingColorType: " + contentPrintingColorType)
    // console.log("contentPageCount: " + contentPageCount)
    // console.log("contentPaperType: " + contentPaperType)
    // console.log("flyleaf: " + flyleaf)
    // console.log("flyleafColorType: " + flyleafColorType)
    // console.log("flyleafCountPerBook: " + flyleafCountPerBook)
    // console.log("flyleafContentPrinting: " + flyleafContentPrinting)
    // console.log("flyleafContentPrintingValue: " + flyleafContentPrintingValue)
    // console.log("flyleafLocation: " + flyleafLocation)
    // console.log("customerComment: " + customerComment)
    // console.log("pageCountPerBook: " + pageCountPerBook)
    // console.log("bookCount: " + bookCount)
    // console.log("price: " + price)
    // console.log("vat: " + vat)
    // console.log("totalPrice: " + totalPrice)

    let itemOptionFormData = new FormData();
    itemOptionFormData.append("ordererId", ordererId);
    itemOptionFormData.append("itemId", itemId);
    itemOptionFormData.append("itemName", itemName);
    itemOptionFormData.append("paperSize", paperSize);
    itemOptionFormData.append("bindingType", bindingType);
    itemOptionFormData.append("bindingDirection", bindingDirection);
    itemOptionFormData.append("coverPrintingType", coverPrintingType);
    itemOptionFormData.append("coverPrintingColorType", coverPrintingColorType);
    itemOptionFormData.append("coverPaperType", coverPaperType);
    itemOptionFormData.append("coverCoatingType", coverCoatingType);
    itemOptionFormData.append("coverDesignName", coverDesignName);
    itemOptionFormData.append("contentPrintingType", contentPrintingType);
    itemOptionFormData.append("contentPrintingColorType", contentPrintingColorType);
    itemOptionFormData.append("contentPageCount", contentPageCount);
    itemOptionFormData.append("contentPaperType", contentPaperType);
    itemOptionFormData.append("hasFlyleaf", flyleaf);
    itemOptionFormData.append("flyleafColorType", flyleafColorType);
    itemOptionFormData.append("flyleafCountPerBook", flyleafCountPerBook);
    itemOptionFormData.append("hasFlyleafContentPrinting", flyleafContentPrinting);
    itemOptionFormData.append("flyleafContentPrintingValue", flyleafContentPrintingValue);
    itemOptionFormData.append("flyleafInsertLocation", flyleafInsertLocation);
    itemOptionFormData.append("customerComment", customerComment);
    itemOptionFormData.append("pageCountPerBook", pageCountPerBook);
    itemOptionFormData.append("bookCount", bookCount);
    itemOptionFormData.append("itemPrice", itemPrice);
    itemOptionFormData.append("vat", vat);
    itemOptionFormData.append("totalPrice", totalPrice);

    $.ajax({
        url: '/cart',
        type: 'POST',
        data: itemOptionFormData,
        processData: false,
        contentType: false,
        // contentType: "application/x-www-form-urlencoded"
    }).done(function (data, textStatus, jqXHR) {
        window.location.href = "/cart"
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

function toggleAllOrderItem(clickedElement) {
    let orderItems = document.querySelectorAll("input[name='order-item']");
    if(clickedElement.checked) {
        orderItems.forEach(orderItem => orderItem.checked = true);
    } else {
        orderItems.forEach(orderItem => orderItem.checked = false);
    }
}

function getCheckedOrderItemIds() {
    let orderItems = document.querySelectorAll("input[name='order-item']");
    let orderItemIds = [];
    for (let index = 0; index < orderItems.length; index++) {
        if (orderItems[index].checked) {
            orderItemIds.push(orderItems[index].value);
        }
    }
    return orderItemIds;
}

function deleteOrderItems() {
    let orderItemIds = getCheckedOrderItemIds();
    if (orderItemIds.length === 0) {
        alert("삭제할 상품을 선택해주세요");
        return;
    }

    console.log("orderItemIds::" + orderItemIds);
    let formData = {
        "orderItemIds": orderItemIds
    }

    $.ajax({
        url: "/cart/orderitems",
        type: "POST",
        data: JSON.stringify(formData),
        contentType: "application/json"
    }).done(function (data, textStatus, jqXHR) {
        alert("삭제되었습니다");
        location.reload();
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

function order() {
    let orderItemIds = getCheckedOrderItemIds();
    if (orderItemIds.length === 0) {
        alert("주문할 상품을 선택해주세요");
        return;
    }

    console.log("orderItemIds::" + orderItemIds);
    let formData = {
        "orderItemIds": orderItemIds
    }

    $.ajax({
        url: "/orders",
        type: "POST",
        data: JSON.stringify(formData),
        contentType: "application/json"
    }).done(function (data, textStatus, jqXHR) {
        window.location = jqXHR.getResponseHeader("location");
        // window.location.href = "/order";
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

function updatePrice() {
    function updatePriceElementValue(price) {
        let priceElement = document.getElementById("item-price");
        priceElement.value = price;
    }
    function updateVatElementValue(vat) {
        let vatElement = document.getElementById("vat");
        vatElement.value = vat;
    }
    function calculateVat(price) {
        return price * 0.1;
    }
    function updateTotalPriceElementValue(price, vat) {
        // 일의자리 버림
        let sumPriceAndVat = Number(price) + Number(vat);
        let resultForCuttingUnitDigit = sumPriceAndVat - (sumPriceAndVat % 10);
        let totalPriceElement = document.getElementById("total-price");
        totalPriceElement.value = resultForCuttingUnitDigit;
    }

    let price = calculatePrice();
    let vat = calculateVat(price);
    updatePriceElementValue(price);
    updateVatElementValue(vat);
    updateTotalPriceElementValue(price, vat);
}

function calculatePrice() {
    let paperSize = getPaperSize(); // 종이 규격
    let bindingTypePrice = getBindingTypePrice(); // 제본유형
    let coverDesignPrice = getDesignTypePrice(); // 디자인
    let coverCoatingPrice = getCoverCoatingTyprPrice(); // 표지 코팅
    let coverPaperPrice = getCoverPaperTypePrice(paperSize); // 표지 종이
    let contentPaperPrice = getContentPaperTypePrice(paperSize); // 본문 종이
    let flyleafPaperPrice = getFlyleafPaperPrice(); // 간지 종이
    let flyleafContentPrice = getFlyleafContentPrice(); // 간지 컨텐츠 출력

    let pricePerBook =
        Number(bindingTypePrice) +
        Number(coverDesignPrice) +
        Number(coverCoatingPrice) +
        Number(coverPaperPrice) +
        Number(contentPaperPrice) +
        Number(flyleafPaperPrice) +
        Number(flyleafContentPrice);

    let bookCount = document.getElementById("book-count").value;
    let price = Number(pricePerBook) * Number(bookCount);

    console.log('제본유형: ' + bindingTypePrice)
    console.log('표지디자인: ' + coverDesignPrice)
    console.log('표지코팅: ' + coverCoatingPrice)
    console.log('표지종이: ' + coverPaperPrice)
    console.log('본문종이: ' + contentPaperPrice)
    console.log('간지종이: ' + flyleafPaperPrice)
    console.log('간지컨텐츠: ' + flyleafContentPrice)
    console.log('1권당 비용: ' + pricePerBook);

    return price;
}

function getPaperSize() {
    let paperSizeElement = document.getElementById("paper-size");
    return paperSizeElement.options[paperSizeElement.selectedIndex].value;
}

function getBindingTypePrice() {
    let bindingTypeElement = document.getElementById("binding-type");
    return bindingTypeElement.options[bindingTypeElement.selectedIndex].dataset.price;
}

function getDesignTypePrice() {
    let coverDesignNameElement = document.getElementById("cover-design-name");
    return coverDesignNameElement.options[coverDesignNameElement.selectedIndex].dataset.price;
}

function getCoverCoatingTyprPrice() {
    let coverCoatingTypeElement = document.getElementById("cover-coating-type");
    return coverCoatingTypeElement.options[coverCoatingTypeElement.selectedIndex].dataset.price;
}

function getCoverPaperTypePrice(paperSize) {
    // 출력 유형 (단면/양면)
    let coverPrintingTypeElement = document.getElementById("cover-printing-type");
    let coverPrintingType = coverPrintingTypeElement.options[coverPrintingTypeElement.selectedIndex].value;
    // 출력 색상 유형 (흑백/컬러)
    let coverPrintingColorTypeElement = document.getElementById("cover-printing-color-type");
    let coverPrintingColorType = coverPrintingColorTypeElement.options[coverPrintingColorTypeElement.selectedIndex].value;
    // 종이 유형
    let coverPaperTypeElement = document.getElementById("cover-paper-type");
    let coverPaperType = coverPaperTypeElement.options[coverPrintingTypeElement.selectedIndex].value;
    let resultOfSearchingPaper = paperPriceTable
        .filter(option => option.category === 'cover')
        .filter(option => option.name === coverPaperType)
        .filter(option => option.printingColor === coverPrintingColorType)
        .filter(option => option.size === paperSize);

    let paperPrice = null;
    if (resultOfSearchingPaper.length > 0) {
        paperPrice = resultOfSearchingPaper[0]
        enableOrderButton();
    }
    if (resultOfSearchingPaper.length === 0) {
        // 종이 가격이 등록되지 않아서 발생
        alert('해당 옵션을 선택하여 주문할 수 없습니다.\n관리자에게 문의하시기 바랍니다.')
        disableOrderButton();
        return;
    }

    let coverPaperSingleSidePrice = paperPrice.singleSidePrice;
    let coverPaperDoubleSidePrice = paperPrice.doubleSidePrice;
    let coverPageCount = 2; // 표지는 첫장, 마지막장 겉면만 해서 2페이지
    return coverPageCount * coverPaperSingleSidePrice;
}

function getContentPaperTypePrice(paperSize) {
    // 출력 유형 (양면/단면)
    let contentPrintingTypeElement = document.getElementById("content-printing-type");
    let contentPrintingType = contentPrintingTypeElement.options[contentPrintingTypeElement.selectedIndex].value;
    // 출력 색상 유형 (흑백/컬러)
    let contentPrintingColorTypeElement = document.getElementById("content-printing-color-type");
    let contentPrintingColorType = contentPrintingColorTypeElement.options[contentPrintingColorTypeElement.selectedIndex].value;
    // 종이 유형
    let contentPaperTypeElement = document.getElementById("content-paper-type");
    let contentPaperType = contentPaperTypeElement.options[contentPrintingTypeElement.selectedIndex].value;
    let resultOfSearchingPaper = paperPriceTable
        .filter(option => option.category === 'content')
        .filter(option => option.name === contentPaperType)
        .filter(option => option.printingColor === contentPrintingColorType)
        .filter(option => option.size === paperSize);

    let paperPrice = null;
    if (resultOfSearchingPaper.length > 0) {
        paperPrice = resultOfSearchingPaper[0]
        enableOrderButton();
    }
    if (resultOfSearchingPaper.length === 0) {
        // 종이 가격이 등록되지 않아서 발생
        alert('해당 옵션을 선택하여 주문할 수 없습니다.\n다른 옵션을 선택해주세요.')
        disableOrderButton();
        return;
    }

    let contentPaperSingleSidePrice = paperPrice.singleSidePrice;
    let contentPaperDoubleSidePrice = paperPrice.doubleSidePrice;
    let contentPageCount = document.getElementById("content-page-count").value;

    if (contentPrintingType === '단면') {
        return contentPageCount * contentPaperSingleSidePrice;
    }
    if (contentPaperType === '양면') {
        // 양면 출력은 1장 출력을 말하는 것이다
        // 2페이지가 1장이므로 총 페이지 수를 반으로 나누고 반올림하여 총 몇장인지 구한다
        let contentPage = Math.round(contentPageCount / 2);
        return contentPage * contentPaperDoubleSidePrice;
    }

    alert('본문 종이 금액이 정상적으로 처리되지 않았습니다.\n관리자에게 문의하세요.');
}

function getFlyleafPaperPrice() {
    let checkedFlyleafElement = document.querySelector("input[name=flyleaf]:checked");
    let hasFlyleaf = checkedFlyleafElement.value;
    if (hasFlyleaf === 'true') {
        let flyleafPriceElement = document.getElementById("flyleaf-color-type");
        let flyleafPricePer2Page =  flyleafPriceElement.options[flyleafPriceElement.selectedIndex].dataset.price // 간지 1장당 가격
        let flyleafCount = document.getElementById("flyleaf-count-per-book").value;
        return flyleafCount * flyleafPricePer2Page;
    }
    return 0;
}

function getFlyleafContentPrice() {
    let checkedFlyleafElement = document.querySelector("input[name=flyleaf-content-printing]:checked");
    let hasFlyleafContent = checkedFlyleafElement.value;
    if (hasFlyleafContent === 'true') {
        return checkedFlyleafElement.dataset.flyleafContentPrice;
    }
    return 0;
}