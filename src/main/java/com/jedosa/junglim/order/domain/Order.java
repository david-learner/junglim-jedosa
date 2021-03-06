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
import java.util.Optional;

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
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    @Embedded
    private DeliveryInfo deliveryInfo;
    @OneToOne
    private Payment payment;
    private LocalDateTime createdDateTime = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order toUpdatedOrder(DeliveryInfo deliveryInfo, Payment payment) {
        return Order.builder()
                .id(id)
                .orderer(orderer)
                .totalPrice(totalPrice)
                .allItemsTotalPrice(allItemsTotalPrice)
                .status(status)
                .orderItems(orderItems)
                .deliveryInfo(deliveryInfo)
                .payment(payment)
                .createdDateTime(createdDateTime)
                .build();
    }

    public static Order of(Account orderer) {

        return Order.builder()
                .orderer(orderer)
                .status(OrderStatus.ORDER_WAITING)
                .deliveryInfo(new DeliveryInfo())
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
    
    // todo ?????? ??????????????? ??????????????? ????????? ?????? ????????? ???????????? ????????? ?????? ????????? ??????????????? ???????????? ??????

    // ??? ?????? = ?????? ???????????? + ?????????
    private void updateTotalPrice() {
        this.totalPrice = calculateTotalPrice();
    }

    private BigDecimal calculateSumOfAllItemPrices() {
        return orderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTotalPrice() {
        // ????????? ??? ????????? ?????? ??? ??????
        if (deliveryInfo.getDeliveryType() == DeliveryType.PARCEL) {
            return calculateSumOfAllItemPrices().add(deliveryInfo.getDeliveryFee());
        }
        return calculateSumOfAllItemPrices();
    }

    public void ordered() {
        this.status = OrderStatus.ORDER_COMPLETE;
    }

    public Boolean isOrdered() {
        return status != OrderStatus.ORDER_WAITING;
    }

    public void removeOrderItemsFromCart() {
        this.orderItems.forEach(OrderItem::removeFromCart);
    }

    public Boolean isValidAmount(BigDecimal paidAmount) {
        return getTotalPrice().compareTo(paidAmount) == 0;
    }
}


