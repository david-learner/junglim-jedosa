package com.jedosa.junglim.order.domain;

import com.jedosa.junglim.item.domain.option.ItemOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder // AllArgs~ 없이 Builder와 NoArgs만 쓸 수 없다
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Order order;
    private Long ordererId;
    private Long itemId;
    private String itemName;
    private BigDecimal itemPrice;
    private BigDecimal vat;
    private BigDecimal totalPrice; // itemPrice + vat
    private LocalDateTime generatedDateTime; // OrderItem 생성일시
    private Boolean isDeleted = Boolean.FALSE;
    private Boolean isInCart = Boolean.TRUE;

    @Embedded
    private ItemOption itemOption;

    public void deleted() {
        this.isDeleted = true;
    }

    public void updateOrder(Order order) {
        this.order = order;
    }

    public void removeFromCart() {
        this.isInCart = Boolean.FALSE;
    }


}
