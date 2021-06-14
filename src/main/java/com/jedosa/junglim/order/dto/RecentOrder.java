package com.jedosa.junglim.order.dto;

import com.jedosa.junglim.order.domain.Order;
import com.jedosa.junglim.util.AddressBlidner;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class RecentOrder {
    private String name;
    private String address;
    private String status;
    private String date;

    public RecentOrder(Order order) {
        this.name = order.getOrderer().getName();
        this.address = AddressBlidner.blindAsStar(order.getOrderer().getAddress().getAddress());
        this.status = order.getStatus().toLabelForKorean();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = order.getCreatedDateTime().format(formatter);
    }
}
