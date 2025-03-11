package aston.intensive.card_service.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.*

@Table("terms")
data class Terms(

    @Id
    val id: UUID? = null,

    val costPerMonth: BigDecimal,

    val transferCommission: BigDecimal,

    val cashWithdrawalCommission: BigDecimal
)
