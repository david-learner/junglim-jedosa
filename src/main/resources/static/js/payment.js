window.onload = function () {
    var IMP = window.IMP;
    IMP.init('imp75742611');
}
// todo 결제 수단에 따른 파라미터 변경
function getPaymentMethodCode() {
    let chosenPaymentMethod = document.querySelector("input[name=payment-method]:checked").value;
    if (chosenPaymentMethod === "신용카드") {
        return "card";
    }
    return "no-account-deposit" // 무통장 입금
}

function getTotalPrice() {
    let deliveryType = document.querySelector("input[name=delivery-type]:checked").value;
    if (deliveryType === "PARCEL") {
        return document.getElementById("total-price-with-parcel-fee").textContent;
    }
    if (deliveryType === "VISIT") {
        return document.getElementById("total-price-without-parcel-fee").textContent;
    }
}

function pay() {

    // 데이터 가져오기
    // Order
    let orderId = document.getElementById("order-id").value;
    let totalPrice = getTotalPrice();

    // DeliveryInfo
    let receiverName = document.getElementById("receiver-name").value;
    let receiverEmail = document.getElementById("receiver-email").value;
    let receiverPhone = document.getElementById("receiver-phone").value;
    let receiverAddressZipcode = document.getElementById("receiver-address-zipcode").value;
    let receiverAddress = document.getElementById("receiver-address").value;
    let receiverDetailedAddress = document.getElementById("receiver-detailed-address").value;
    let deliveryType = document.querySelector("input[name=delivery-type]:checked").value;
    let deliveryFee = getDeliveryFee();
    let messageToDeliver = document.getElementById("message-to-deliver").value;
    
    // Payment
    let paymentMethod = document.querySelector("input[name=payment-method]:checked").value;
    let impUid = "";
    let orderUid = "merchant_" + new Date().getTime();
    let orderName = "주문명:결제테스트"; // 구매 상품 이름으로 변경
    let amount = getTotalPrice();
    let ordererName = document.getElementById("orderer-name").innerText;
    let ordererPhone = document.getElementById("orderer-phone").innerText;
    let ordererEmail = document.getElementById("orderer-email").innerText;
    let accountHolderName = document.getElementById("account-holder-name").value;

    // For Import Payment
    let ordererAddressZipcode = document.getElementById("orderer-address-zipcode").innerText;
    let ordererAddress = document.getElementById("orderer-address").innerText;
    let ordererDetailedAddress = document.getElementById("orderer-detailed-address").innerText;
    let paymentMethodCode = getPaymentMethodCode(); // 카드 결제시 아임포트로 보낼 코드
    let paymentStatus = "WAITING"; // todo 추후 상태 변경 필요

    let accountHolderNameElement = document.getElementById("account-holder-name");

    // 결제 및 결제 정보 저장을 위한 데이터
    let paymentFormData = new FormData();
    paymentFormData.append("paymentMethod", paymentMethod);
    paymentFormData.append("amount", amount);
    paymentFormData.append("ordererName", ordererName);
    paymentFormData.append("ordererPhone", ordererPhone);
    paymentFormData.append("ordererEmail", ordererEmail);

    // 주문서 내용 업데이트를 위한 데이터
    let updatedOrderData = {

        // Order
        "totalPrice" : totalPrice,

        // DeliveryInfo
        "receiverName" : receiverName,
        "receiverPhone" : receiverPhone,
        "receiverEmail" : receiverEmail,
        "receiverAddress" : {
            "zipcode": receiverAddressZipcode,
            "address": receiverAddress,
            "detailedAddress": receiverDetailedAddress
        },
        "deliveryType" : deliveryType,
        "deliveryFee" : deliveryFee,
        "messageToDeliver" : messageToDeliver,

        // Payment
        "paymentMethod" : paymentMethod,
        "amount" : amount,
        "ordererName" : ordererName,
        "ordererPhone" : ordererPhone,
        "ordererEmail" : ordererEmail,
        "accountHolderName" : accountHolderName,
        "paymentStatus" : paymentStatus
    }

    // 무통장 입금 결제 처리
    if (paymentMethodCode === "no-account-deposit") {

        if (accountHolderName === "") {
            alert("예금주명을 입력하세요");
            accountHolderNameElement.focus();
            return;
        }
        let forwardToPaymentComplete = function () {
            window.location.href = "/payments/complete";
        }

        updateOrder(orderId, updatedOrderData, forwardToPaymentComplete());
        return;
    }

    // 카드 결제 처리
    updatedOrderData["orderUid"] = orderUid;
    IMP.request_pay({
        pg : 'html5_inicis', // version 1.1.0부터 지원.
        pay_method : paymentMethodCode,
        merchant_uid : orderUid,
        name : orderName,
        amount : amount,
        buyer_email : ordererEmail,
        buyer_name : ordererName,
        buyer_tel : ordererPhone,
        buyer_addr : ordererAddress + ' ' + ordererDetailedAddress,
        buyer_postcode : ordererAddressZipcode,
        m_redirect_url : '/payments/complete'
    }, function(rsp) {
        let checkCardPaymentValidation = function (rsp, orderUid, impUid, applyNum) {

            let validationRequestUrl =
                "/payments/card-payment-validation" + "?" +
                "impuid=" + impUid + "&" +
                "orderuid=" + orderUid + "&" +
                "applynum=" + applyNum + "&" +
                "orderId=" + orderId;

            $.ajax({
                url: validationRequestUrl,
                type: "GET",
            }).done(function (data, textStatus, jqXHR) {
                alert("성공");
                alert(data);
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseJSON.message);
            });
        }

        if (rsp.success) {

            let paidAt = new Date(rsp.paid_at*1000) // *1000은 millisecond로 만들기 위해서.

            impUid = rsp.imp_uid; // 아임포트 거래 고유번호
            let applyNum = rsp.apply_num; // 카드사 승인번호
            updatedOrderData["impUid"] = impUid
            updatedOrderData["applyNum"] = applyNum
            updatedOrderData["paidDateTime"] = paidAt;

            updateOrder(orderId, updatedOrderData, checkCardPaymentValidation(rsp, orderUid, impUid, applyNum));
        }

        // let msg = "";
        // if ( rsp.success ) {
        //     msg = '결제가 완료되었습니다.';
        //     msg += '고유ID : ' + rsp.imp_uid;
        //     msg += '상점 거래ID : ' + rsp.merchant_uid;
        //     msg += '결제 금액 : ' + rsp.paid_amount;
        //     msg += '카드 승인번호 : ' + rsp.apply_num;
        // } else {
        //     msg = '결제에 실패하였습니다.';
        //     msg += '에러내용 : ' + rsp.error_msg;
        // }
        // alert(msg);
    });
}

// 주문서 내 정보 업데이트
// * 주문서 상태 ordered
// * 주문서 내 OrderItems를 카트에서 제거
// * 배송정보
// * 결제정보
function updateOrder(orderId, updatedOrderData, callback_success) {
    $.ajax({
        url: "/orders/" + orderId + "/order",
        type: "PATCH",
        data: JSON.stringify(updatedOrderData),
        contentType: "application/json"
    }).done(function (data, textStatus, jqXHR) {
        callback_success && callback_success();
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

// DeliveryType에 따른 배송비 반환
function getDeliveryFee() {

    let deliveryType = document.querySelector("input[name=delivery-type]:checked").value;
    if (deliveryType === "PARCEL") {
        return document.getElementById("delivery-fee-parcel").innerText;
    }
    if (deliveryType === "VISIT") {
        return document.getElementById("delivery-fee-visit").innerText;
    }
}