package com.jedosa.junglim.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderItemIdsDto {

    private List<Long> orderItemIds = new ArrayList<>();

    public OrderItemIdsDto() {
    }
}
