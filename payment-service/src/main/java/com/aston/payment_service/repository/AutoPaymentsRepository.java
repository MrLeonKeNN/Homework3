package com.aston.payment_service.repository;

import com.aston.payment_service.entity.AutoPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AutoPaymentsRepository extends JpaRepository<AutoPayments, UUID> {

    @Query("FROM AutoPayments a WHERE a.nextPaymentDate <= CURRENT_TIMESTAMP")
    List<AutoPayments> getAutoPaymentsForCrone();
}