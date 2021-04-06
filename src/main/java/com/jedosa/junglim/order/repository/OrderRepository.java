package com.jedosa.junglim.order.repository;

import com.jedosa.junglim.order.domain.Order;
import com.jedosa.junglim.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}