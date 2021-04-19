package com.jedosa.junglim.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentMethod;
    private String impUid;
    private String orderUid; // 고유주문번호. Order의 Id와는 별개
    private BigDecimal amount;
    private String ordererName;
    private String ordererPhone;
    private String ordererEmail;
    private String accountHolderName; // 예금주명
    private String applyNum; // 카드사 승인번호
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.WAITING;
    // 결제 정보 생성 일시
    private LocalDateTime createdDateTime = LocalDateTime.now();
    // 결제 완료 일시
    private LocalDateTime paidDateTime;
    // 결제 취소 일시
    private LocalDateTime canceledDateTime;

    public Payment(String paymentMethod,
                   String orderUid,
                   BigDecimal amount,
                   String ordererName,
                   String ordererPhone,
                   String ordererEmail,
                   String accountHolderName) {
        this.paymentMethod = paymentMethod;
        this.orderUid = orderUid;
        this.amount = amount;
        this.ordererName = ordererName;
        this.ordererPhone = ordererPhone;
        this.ordererEmail = ordererEmail;
        this.accountHolderName = accountHolderName;
    }

    public void paid() {
        this.paymentStatus = PaymentStatus.PAID;
        this.paidDateTime = LocalDateTime.now();
    }

    public void canceled() {
        this.paymentStatus = PaymentStatus.CANCELED;
        this.canceledDateTime = LocalDateTime.now();
    }

    public Boolean isCanceled() {
        return this.paymentStatus.equals(PaymentStatus.CANCELED);
    }
}
