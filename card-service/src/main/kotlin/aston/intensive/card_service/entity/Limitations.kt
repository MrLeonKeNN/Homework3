package aston.intensive.card_service.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.math.BigInteger
import java.util.UUID

@Table("limitations")
data class Limitations(

    @Id
    val id: UUID? = null,

    val amountPerDay: BigDecimal,

    val amountPerMonth: BigDecimal,

    val amountPerOperation: BigInteger,

    val cashWithdrawalPerDay: BigInteger,

    val operationPerDay: Int,

    val operationPerMonth: Int,
)
