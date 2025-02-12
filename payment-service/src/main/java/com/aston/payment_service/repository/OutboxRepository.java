package com.aston.payment_service.repository;

import com.aston.payment_service.entity.Outbox;
import com.aston.payment_service.entity.enums.OutboxStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("FROM Outbox o  WHERE o.status = :status")
    List<Outbox> findWaitingOutbox(OutboxStatus status);

    @Modifying
    @Query("UPDATE Outbox o SET o.status = :value WHERE o.id IN :ids")
    int updateStatus(@Param("value") OutboxStatus value, @Param("ids") List<Long> ids);
}