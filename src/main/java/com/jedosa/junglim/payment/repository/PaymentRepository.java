package com.jedosa.junglim.payment.repository;

import com.jedosa.junglim.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
