package com.jedosa.junglim.order.dto;

import com.jedosa.junglim.article.domain.Pagination;
import com.jedosa.junglim.order.domain.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdersDto {
    List<OrderDto> orderDtos;
    Pagination pagination;

    public OrdersDto(List<OrderDto> orderDtos, Pagination pagination) {
        this.orderDtos = orderDtos;
        this.pagination = pagination;
    }
}
