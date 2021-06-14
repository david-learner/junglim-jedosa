package com.jedosa.junglim.order.dto;

import com.jedosa.junglim.article.domain.Pagination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdersDto {
    List<ResponseAdminOrderListItemDto> orderDtos;
    Pagination pagination;

    public OrdersDto(List<ResponseAdminOrderListItemDto> orderDtos, Pagination pagination) {
        this.orderDtos = orderDtos;
        this.pagination = pagination;
    }
}
