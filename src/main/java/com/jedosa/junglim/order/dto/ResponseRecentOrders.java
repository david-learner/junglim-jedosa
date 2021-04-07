package com.jedosa.junglim.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseRecentOrders {
    List<RecentOrder> orders;

    public ResponseRecentOrders(List<RecentOrder> orders) {
        this.orders = orders;
    }
}
