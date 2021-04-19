package com.jedosa.junglim.order.dto;

import com.jedosa.junglim.order.domain.Order;

public class RequestOrderDto extends OrderDto {

    // OrderItems
    OrderItemIdsDto orderItemIdsDto;

    public RequestOrderDto(Order order) {
        super(order);
    }
}
