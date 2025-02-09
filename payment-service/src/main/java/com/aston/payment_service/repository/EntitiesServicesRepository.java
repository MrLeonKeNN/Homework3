package com.aston.payment_service.repository;

import com.aston.payment_service.entity.EntitiesServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntitiesServicesRepository extends JpaRepository<EntitiesServices, UUID> {
    EntitiesServices findByEntity_ClientId(UUID entityClientId);
}