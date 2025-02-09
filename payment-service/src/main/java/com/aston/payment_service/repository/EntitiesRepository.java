package com.aston.payment_service.repository;

import com.aston.payment_service.entity.Entities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EntitiesRepository extends JpaRepository<Entities, UUID> {

    Optional<Entities> findByClientId(UUID clientId);

}