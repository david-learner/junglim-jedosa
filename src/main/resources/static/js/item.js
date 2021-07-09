window.onload = function () {
    // 간지 옵션 미노출
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
        console.log("HAS FLYLEAF");
        sumForPageCountPerBook = contentPageCount + flyleafCountPerBook;
    } else { // !hasFlyleaf
        console.log("HAS NOT FLYLEAF");
        sumForPageCountPerBook = contentPageCount;
    }
    console.log("Content Page Count: " + contentPageCount)
    console.log("Has Flyleaf: " + hasFlyleaf)
    console.log("flyleafCountPerBook: " + flyleafCountPerBook)
    let pageCountPerBook = document.getElementById("page-count-per-book");
    pageCountPerBook.value = sumForPageCountPerBook;
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