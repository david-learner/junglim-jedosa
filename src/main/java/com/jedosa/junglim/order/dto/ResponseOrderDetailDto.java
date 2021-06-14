package com.jedosa.junglim.order.dto;

import com.jedosa.junglim.order.domain.DeliveryType;
import com.jedosa.junglim.order.domain.Order;
import com.jedosa.junglim.order.domain.OrderStatus;
import com.jedosa.junglim.payment.domain.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ResponseOrderDetailDto {
    // Order
    private Long orderId;
    private BigDecimal totalPrice;
    private BigDecimal allItemsTotalPrice;
    private OrderStatus status; // 주문상태
    private List<OrderItemDto> orderItemDtos;
    private LocalDateTime createdDateTime;
    // Orderer
    private Long ordererId;
    private String ordererEmail;
    private String ordererName;
    private String ordererPhone;
    // Orderer Address
    private String ordererZipcode;
    private String ordererAddress;
    private String ordererDetailedAddress;
    // DeliveryInfo
    private String receiverName;
    private String receiverPhone;
//    private String receiverEmail;
    private String receiverZipcode;
    private String receiverAddress;
    private String receiverDetailedAddress;
    private DeliveryType deliveryType;
    private BigDecimal deliveryFee;
    private String messageToDeliver;
    // Payment
    private String impUid; // 아임포트 거래 고유번호
    private String orderUid; // 고유주문번호. Order의 Id와는 별개
    private BigDecimal amount;
    private String accountHolderName;
    private String applyNum; // 카드사 승인번호
    private String paymentMethod; // 결제수단
    private PaymentStatus paymentStatus; // 결제상태
    private LocalDateTime paidDateTime; // 결제시간

    public ResponseOrderDetailDto(Order order) {
        // order
        this.orderId = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.allItemsTotalPrice = order.getAllItemsTotalPrice();
        this.status = order.getStatus();
        this.orderItemDtos = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.createdDateTime = order.getCreatedDateTime();
        // Orderer
        this.ordererId = order.getOrderer().getId();
        this.ordererEmail = order.getOrderer().getEmail();
        this.ordererName = order.getOrderer().getName();
        this.ordererPhone = order.getOrderer().getPhone();
        this.ordererZipcode = order.getOrderer().getAddress().getZipcode();
        this.ordererAddress = order.getOrderer().getAddress().getAddress();
        this.ordererDetailedAddress = order.getOrderer().getAddress().getDetailedAddress();
        // Delivery Info
        this.receiverName = order.getDeliveryInfo().getReceiverName();
        this.receiverPhone = order.getDeliveryInfo().getReceiverPhone();
//        this.receiverEmail = order.getDeliveryInfo().getReceiverEmail();
        this.receiverZipcode = order.getDeliveryInfo().getReceiverAddress().getZipcode();
        this.receiverAddress = order.getDeliveryInfo().getReceiverAddress().getAddress();
        this.receiverDetailedAddress = order.getDeliveryInfo().getReceiverAddress().getDetailedAddress();
        this.deliveryType = order.getDeliveryInfo().getDeliveryType();
        this.deliveryFee = order.getDeliveryInfo().getDeliveryFee();
        this.messageToDeliver = order.getDeliveryInfo().getMessageToDeliver();
        // Payment
        this.impUid = order.getPayment().getImpUid();
        this.orderUid = order.getPayment().getOrderUid();
        this.amount = order.getPayment().getAmount();
        this.accountHolderName = order.getPayment().getAccountHolderName();
        this.applyNum = order.getPayment().getApplyNum();
        this.paymentMethod = order.getPayment().getPaymentMethod();
        this.paymentStatus = order.getPayment().getPaymentStatus();
        this.paidDateTime = order.getPayment().getPaidDateTime();
    }

    public String getCreatedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.createdDateTime.format(formatter);
    }
}
