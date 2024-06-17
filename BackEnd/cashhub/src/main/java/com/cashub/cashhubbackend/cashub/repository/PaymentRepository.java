package com.cashub.cashhubbackend.cashub.repository;

import com.cashub.cashhubbackend.cashub.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}