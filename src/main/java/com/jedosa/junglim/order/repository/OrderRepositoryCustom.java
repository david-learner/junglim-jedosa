package com.jedosa.junglim.order.repository;

import com.jedosa.junglim.order.domain.Order;
import com.jedosa.junglim.order.domain.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositoryCustom {
    Page<Order> search(OrderSearchCondition condition, Pageable pageable);
    List<Order> findByLimit(Integer limit);
}