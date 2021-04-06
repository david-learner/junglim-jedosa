package com.jedosa.junglim.order.dto;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.account.domain.Address;
import com.jedosa.junglim.order.domain.DeliveryInfo;
import com.jedosa.junglim.order.domain.DeliveryType;
import com.jedosa.junglim.payment.domain.Payment;
import com.jedosa.junglim.payment.domain.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {

    // Order
    private Long orderId;
    private Account orderer;
    private BigDecimal totalPrice;
    private BigDecimal allItemsTotalPrice;

    // DeliveryInfo
    private String receiverName;
    private String receiverPhone;
    private Address receiverAddress;
    private DeliveryType deliveryType;
    private BigDecimal deliveryFee;
    private String messageToDeliver;

    // Payment
    private String impUid; // 아임포트 거래 고유번호
    private String orderUid; // 고유주문번호. Order의 Id와는 별개
    private BigDecimal amount;
    private String ordererName;
    private String ordererPhone;
    private String ordererEmail;
    private String accountHolderName;
    private String applyNum; // 카드사 승인번호
    private String paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime paidDateTime;

    // deliveryInfo
    public DeliveryInfo toDeliveryInfo() {
        return DeliveryInfo.builder()
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverAddress(receiverAddress)
                .deliveryType(deliveryType)
                .deliveryFee(deliveryFee)
                .messageToDeliver(messageToDeliver)
                .build();
    }

    public Payment toPayment() {
        return Payment.builder()
                .impUid(impUid)
                .orderUid(orderUid)
                .amount(amount)
                .ordererName(ordererName)
                .ordererPhone(ordererPhone)
                .ordererEmail(ordererEmail)
                .accountHolderName(accountHolderName)
                .applyNum(applyNum)
                .paymentMethod(paymentMethod)
                .paymentStatus(paymentStatus)
                .createdDateTime(LocalDateTime.now())
                .paidDateTime(paidDateTime)
                .build();
    }
}
