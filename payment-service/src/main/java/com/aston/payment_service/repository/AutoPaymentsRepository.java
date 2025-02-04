package com.aston.payment_service.repository;

import com.aston.payment_service.entity.AutoPayments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutoPaymentsRepository extends JpaRepository<AutoPayments, UUID> {
}