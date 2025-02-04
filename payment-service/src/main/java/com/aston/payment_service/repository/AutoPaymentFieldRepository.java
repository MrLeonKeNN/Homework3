package com.aston.payment_service.repository;

import com.aston.payment_service.entity.AutoPaymentField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutoPaymentFieldRepository extends JpaRepository<AutoPaymentField, UUID> {
}