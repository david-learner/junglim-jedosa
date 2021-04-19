package com.jedosa.junglim.order.dto;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.account.domain.Address;
import com.jedosa.junglim.order.domain.DeliveryInfo;
import com.jedosa.junglim.order.domain.DeliveryType;
import com.jedosa.junglim.order.domain.Order;
import com.jedosa.junglim.order.domain.OrderStatus;
import com.jedosa.junglim.payment.domain.Payment;
import com.jedosa.junglim.payment.domain.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private List<OrderItemDto> orderItemDtos;
    private OrderStatus status;

    // board
    private Long boardItemSequence; // 게시판 나타날 주문의 순서를 나타내는 번호

    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.orderer = order.getOrderer(); // todo account dto로 교체
        this.totalPrice = order.getTotalPrice();
        this.allItemsTotalPrice = order.getAllItemsTotalPrice();
        this.receiverName = order.getDeliveryInfo().getReceiverName();
        this.receiverPhone = order.getDeliveryInfo().getReceiverPhone();
        this.receiverAddress = order.getDeliveryInfo().getReceiverAddress();
        this.deliveryType = order.getDeliveryInfo().getDeliveryType();
        this.deliveryFee = order.getDeliveryInfo().getDeliveryFee();
        this.messageToDeliver = order.getDeliveryInfo().getMessageToDeliver();
        this.impUid = order.getPayment().getImpUid();
        this.orderUid = order.getPayment().getOrderUid();
        this.amount = order.getPayment().getAmount();
        this.ordererName = order.getPayment().getOrdererName();
        this.ordererPhone = order.getPayment().getOrdererPhone();
        this.ordererEmail = order.getPayment().getOrdererEmail();
        this.accountHolderName = order.getPayment().getAccountHolderName();
        this.applyNum = order.getPayment().getApplyNum();
        this.paymentMethod = order.getPayment().getPaymentMethod();
        this.paymentStatus = order.getPayment().getPaymentStatus();
        this.paidDateTime = order.getPayment().getPaidDateTime();
        this.orderItemDtos = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.status = order.getStatus();
    }

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
