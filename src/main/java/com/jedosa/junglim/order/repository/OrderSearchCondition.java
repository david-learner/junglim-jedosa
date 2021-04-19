package com.jedosa.junglim.order.repository;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderSearchCondition {
    private Integer page;

    public OrderSearchCondition(Integer page) {
        this.page = page;
    }
}
