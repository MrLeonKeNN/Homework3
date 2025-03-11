package aston.intensive.card_service.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Table("card_application")
data class CardApplication(

    @Id
    val id: UUID? = null,

    val productId: UUID,

    val paymentSystem: String,

    val currency: String,

    val applicationStatus: String,

    val firstName: String,

    val lastName: String,

    val patronymic: String,

    val mobilePhone: String,

    val email: String,

    val birthDate: Instant,

    val citizenship: String,

    val cardDeliveryMethod: String,

    val clientAddress: String,

    val code: Int
)