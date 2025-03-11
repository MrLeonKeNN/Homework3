package aston.intensive.card_service.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/v1")
interface CardController {

//    @PostMapping("/card-application")
//    fun createCardApplication(@RequestBody @Valid CreateCardDto:CreateCardDto): ResponseEntity<String>
}