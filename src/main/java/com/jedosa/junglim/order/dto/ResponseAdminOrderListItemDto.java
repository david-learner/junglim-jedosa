package com.jedosa.junglim.order.dto;

import com.jedosa.junglim.order.domain.Order;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ResponseAdminOrderListItemDto {

    private Long orderId;
    private String ordererName;
    private String itemName;
    private String totalPrice;
    private String deliveryType;
    private String status;
    private Long boardItemSequence; // 게시판 나타날 주문의 순서를 나타내는 번호
    private String orderCreatedDate;

    public ResponseAdminOrderListItemDto(Order order) {
        this.orderId = order.getId();
        this.ordererName = order.getOrderer().getName();
        this.itemName = order.getOrderItems().get(0).getItemName();
        this.totalPrice = order.getTotalPrice().toBigInteger().toString();
        this.status = order.getStatus().toLabelForKorean();
        this.deliveryType = order.getDeliveryInfo().getDeliveryType().getLabelForKorean();
        this.orderCreatedDate = order.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
