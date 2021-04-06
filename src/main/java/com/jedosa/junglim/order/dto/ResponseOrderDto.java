package com.jedosa.junglim.order.dto;

import com.jedosa.junglim.order.domain.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ResponseOrderDto extends OrderDto {

    public ResponseOrderDto(Order order) {
        super.setOrderId(order.getId());
        super.setOrderer(order.getOrderer());
        super.setDeliveryFee(order.getDeliveryInfo().getDeliveryFee());
        super.setTotalPrice(order.getTotalPrice());
        super.setAllItemsTotalPrice(order.getAllItemsTotalPrice());
        this.orderItemDtos = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }

    // OrderItems
    List<OrderItemDto> orderItemDtos;
}
