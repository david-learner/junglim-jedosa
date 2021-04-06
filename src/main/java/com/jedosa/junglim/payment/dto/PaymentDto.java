package com.jedosa.junglim.payment.dto;

import com.jedosa.junglim.payment.domain.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PaymentDto {

    private String paymentMethod;
    private String orderUid;
    private BigDecimal amount;
    private String ordererName;
    private String ordererPhone;
    private String ordererEmail;
    private String accountHolderName;

    public Payment toPayment() {
        return new Payment(paymentMethod, orderUid, amount, ordererName, ordererPhone, ordererEmail, accountHolderName);
    }
}
