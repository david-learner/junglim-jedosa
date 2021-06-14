package com.jedosa.junglim.order.dto;

import com.jedosa.junglim.order.domain.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ResponseOrderFormDto {
    // Order
    private Long orderId;
    private String totalPrice;
    private String allItemsTotalPrice;
    private List<OrderItemDto> orderItemDtos;
    // Orderer
    private Long ordererId;
    private String ordererEmail;
    private String ordererName;
    private String ordererPhone;
    private String ordererZipcode;
    private String ordererAddress;
    private String ordererDetailedAddress;

    public ResponseOrderFormDto(Order order) {
        this.orderId = order.getId();
        this.totalPrice = order.getTotalPrice().toBigInteger().toString();
        this.allItemsTotalPrice = order.getAllItemsTotalPrice().toBigInteger().toString();
        this.orderItemDtos = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.ordererId = order.getOrderer().getId();
        this.ordererEmail = order.getOrderer().getEmail();
        this.ordererName = order.getOrderer().getName();
        this.ordererPhone = order.getOrderer().getPhone();
        this.ordererZipcode = order.getOrderer().getAddress().getZipcode();
        this.ordererAddress = order.getOrderer().getAddress().getAddress();
        this.ordererDetailedAddress = order.getOrderer().getAddress().getDetailedAddress();
    }
}
