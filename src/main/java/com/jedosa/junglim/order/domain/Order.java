package com.jedosa.junglim.order.domain;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.payment.domain.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "account_id")
    private Account orderer;
    private BigDecimal totalPrice;
    private BigDecimal allItemsTotalPrice;
    private Boolean isOrdered = Boolean.FALSE;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    @Embedded
    private DeliveryInfo deliveryInfo;
    @OneToOne
    private Payment payment;
    private LocalDateTime createdDateTime = LocalDateTime.now();

    public Order toUpdatedOrder(DeliveryInfo deliveryInfo, Payment payment) {
        return Order.builder()
                .id(id)
                .orderer(orderer)
                .totalPrice(totalPrice)
                .allItemsTotalPrice(allItemsTotalPrice)
                .isOrdered(isOrdered)
                .orderItems(orderItems)
                .deliveryInfo(deliveryInfo)
                .payment(payment)
                .createdDateTime(createdDateTime)
                .build();
    }

    public static Order of(Account orderer, DeliveryInfo deliveryInfo) {

        return Order.builder()
                .orderer(orderer)
                .isOrdered(Boolean.FALSE)
                .deliveryInfo(deliveryInfo)
                .createdDateTime(LocalDateTime.now())
                .build();
    }

    public void addOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        orderItems.forEach(orderItem -> orderItem.updateOrder(this));

        updateAllItemsTotalPrice();
        updateTotalPrice();
    }

    private void updateAllItemsTotalPrice() {
        this.allItemsTotalPrice = calculateSumOfAllItemPrices();
    }
    
    // todo 주문 업데이트시 프론트에서 넘어온 결제 금액과 서버에서 계산한 결제 금액이 일치하는지 검증하는 부분

    // 총 가격 = 모든 상품가격 + 배송비
    private void updateTotalPrice() {
        this.totalPrice = calculateTotalPrice();
    }

    private BigDecimal calculateSumOfAllItemPrices() {
        return orderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTotalPrice() {
        // 택배일 때 배송비 포함 총 가격
        if (deliveryInfo.getDeliveryType().equals(DeliveryType.PARCEL)) {
            return calculateSumOfAllItemPrices().add(deliveryInfo.getDeliveryFee());
        }
        return calculateSumOfAllItemPrices();
    }

    public void ordered() {
        this.isOrdered = Boolean.TRUE;
    }

    public Boolean isOrdered() {
        return isOrdered;
    }

    public void removeOrderItemsFromCart() {
        this.orderItems.forEach(OrderItem::removeFromCart);
    }

    public Boolean isValidAmount(BigDecimal paidAmount) {
        return getTotalPrice().compareTo(paidAmount) == 0;
    }
}


