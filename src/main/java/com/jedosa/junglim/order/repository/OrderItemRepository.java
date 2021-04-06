package com.jedosa.junglim.order.repository;

import com.jedosa.junglim.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrdererIdAndIsDeletedAndIsInCart(Long ordererId, Boolean isDeleted, Boolean isInCart);
    void deleteAllByIdIn(List<Long> ids);
}