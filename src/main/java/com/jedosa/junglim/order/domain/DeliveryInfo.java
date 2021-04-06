package com.jedosa.junglim.order.domain;

import com.jedosa.junglim.account.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInfo {

    private String receiverName;
    private String receiverPhone;
    private Address receiverAddress;
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType = DeliveryType.PARCEL;
    private BigDecimal deliveryFee = BigDecimal.ZERO;
    private String messageToDeliver;

    public DeliveryInfo(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}
