package aston.intensive.card_service.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Table(name = "debit_cards")
data class DebitCard(

    @Id
    val id: UUID? = null,

    @Column("account_id")
    val accountId: String,

    @Column("client_id")
    val clientId: String,

    @Column("product_id")
    val productId: String,

    @Column("secure_data_id")
    val secureDataId: String,

    @Column("first_name")
    val firstName: String,

    @Column("last_name")
    val lastName: String,

    @Column("status")
    val status: String,

    @Column("created_at")
    val createdAt: Instant,

    @Column("expiration_date")
    val expirationDate: Instant,

    @Column("closed_at")
    val closedAt: Instant? = null,

    @Column("push_per_day")
    val pushPerDay: Boolean,

    @Column("amount_per_day")
    val amountPerDay: BigDecimal,

    @Column("cash_withdrawal_per_day")
    val cashWithdrawalPerDay: BigDecimal,

    @Column("payment_system")
    val paymentSystem: String,

    @Column("currency")
    val currency: String,

    @Column("master_card")
    val masterCard: Boolean
)