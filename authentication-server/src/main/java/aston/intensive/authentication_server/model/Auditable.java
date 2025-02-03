package aston.intensive.authentication_server.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

/**
 * Класс для аудита всех entity.
 */
@MappedSuperclass
public class Auditable {

    /**
     * Дата и время создания сущности.
     */
    @Column(name = "created_date")
    @CreatedDate
    private Instant createdDate;

    /**
     * Дата и время последней модификации сущности.
     */
    @Column(name = "last_update_date")
    @LastModifiedDate
    private Instant lastUpdateDate;
}
