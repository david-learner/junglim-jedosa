window.onload = function () {
}

function toggleDeliveryType() {

    let deliveryType = document.querySelector("input[name=delivery-type]:checked").value;
    let parcelForm = document.getElementById("parcel-data")
    let visitForm = document.getElementById("delivery-type-visit")
    let parcelFee = document.getElementById("delivery-fee-parcel")
    let visitFee = document.getElementById("delivery-fee-visit")
    let totalPriceWithParcelFee = document.getElementById("total-price-with-parcel-fee");
    let totalPriceWithoutParcelFee = document.getElementById("total-price-without-parcel-fee");

    // 수령방법 - 택배
    if (deliveryType === "PARCEL") {
        parcelForm.style.display = "";
        parcelFee.style.display = "";
        totalPriceWithParcelFee.style.display = "";
        visitForm.style.display = "none";
        visitFee.style.display = "none";
        totalPriceWithoutParcelFee.style.display = "none";
    }

    // 수령방법 - 방문
    if (deliveryType === "VISIT") {
        parcelForm.style.display = "none";
        parcelFee.style.display = "none";
        totalPriceWithParcelFee.style.display = "none";
        visitForm.style.display = "";
        visitFee.style.display = "";
        totalPriceWithoutParcelFee.style.display = "";
    }

    removeReceiverData();
}

// 수령자 정보 삭제 및 구매자 정보와 동일 체크 해제
function removeReceiverData() {
    document.getElementById("filling-delivery-info").checked = false;
    document.getElementById("receiver-name").value = "";
    document.getElementById("receiver-phone").value = "";
    document.getElementById("receiver-email").value = "";
    document.getElementById("receiver-address-zipcode").value = "";
    document.getElementById("receiver-address").value = "";
    document.getElementById("receiver-detailed-address").value = "";
    document.getElementById("message-to-deliver").value = "";
}

function toggleDeliveryInfo() {
    
    // 수령방법
    let deliveryType = document.querySelector("input[name=delivery-type]:checked").value;

    // 주문자 정보
    let ordererName = document.getElementById("orderer-name");
    let ordererPhone = document.getElementById("orderer-phone");
    let ordererEmail = document.getElementById("orderer-email");
    let ordererZipcode = document.getElementById("orderer-address-zipcode");
    let ordererAddress = document.getElementById("orderer-address");
    let ordererDetailedAddress = document.getElementById("orderer-detailed-address");

    // 수령자 정보
    let receiverName = document.getElementById("receiver-name");
    let receiverPhone = document.getElementById("receiver-phone");
    let receiverEmail = document.getElementById("receiver-email");
    let receiverZipcode = document.getElementById("receiver-address-zipcode")
    let receiverAddress = document.getElementById("receiver-address")
    let receiverDetailedAddress = document.getElementById("receiver-detailed-address")

    let fillingDeliveryInfo = document.getElementById("filling-delivery-info");
    if (fillingDeliveryInfo.checked) {
        receiverName.value = ordererName.textContent;
        receiverPhone.value = ordererPhone.textContent;
        receiverEmail.value = ordererEmail.textContent;
        // 수령 방법이 택배라면 수령지 주소도 채워넣기
        if (deliveryType === "PARCEL") {
            receiverZipcode.value = ordererZipcode.textContent;
            receiverAddress.value = ordererAddress.textContent;
            receiverDetailedAddress.value = ordererDetailedAddress.textContent;
        }
    } else {
        receiverName.value = "";
        receiverPhone.value = "";
        receiverEmail.value = "";
        receiverZipcode.value = "";
        receiverAddress.value = "";
        receiverDetailedAddress.value = "";
    }
}

function togglePaymentMethod() {

    let paymentMethod = document.querySelector("input[name=payment-method]:checked").value;
    let noAccountDepositInfo = document.getElementById("no-account-deposit-info");
    let accountHolderName = document.getElementById("account-holder-name").parentElement.parentElement;

    if(paymentMethod === "신용카드") {
        noAccountDepositInfo.style.display = "none";
        accountHolderName.style.display = "none";
    }
    if (paymentMethod === "무통장입금") {
        noAccountDepositInfo.style.display = "";
        accountHolderName.style.display = "";
    }
}
