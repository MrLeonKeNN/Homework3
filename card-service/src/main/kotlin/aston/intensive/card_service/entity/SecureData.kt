package aston.intensive.card_service.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("secure_data")
data class SecureData(

    @Id
    val id: UUID? = null,

    val pinCode: Int,

    val cvcCode: Int,

    val firstTwelveNumbers: Int,

    val lastFourNumbers: Int
)
