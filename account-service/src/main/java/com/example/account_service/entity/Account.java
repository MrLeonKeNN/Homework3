package com.example.account_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
@AllArgsConstructor 
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "department_id")
    private UUID departmentId;

    @Column(name = "account_number", length = 20)
    private String accountNumber;

    @Column(name = "type_account", length = 10)
    private String typeAccount;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Column(name = "hold_balance")
    private BigDecimal holdBalance;

    @Column(name = "status_name", length = 7)
    private String statusName;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "closed_at")
    private Instant closedAt;

    @Column(name = "block_reason")
    private String blockReason;

    @Column(name = "master_account")
    private Boolean masterAccount;

    @Column(name = "currency_name", length = 3)
    private String currencyName;

    @Column(name = "name_account", length = 50)
    private String nameAccount;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer()
                .getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Account account = (Account) o;
        return getAccountId() != null && Objects.equals(getAccountId(), account.getAccountId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass()
                .hashCode() : getClass().hashCode();
    }
}
