package com.mkacunha.springcommandbus.reactor.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.mkacunha.springcommandbus.reactor.domain.order.command.CreateOrder
import java.util.*

class CreateOrderRequest(
    @JsonProperty("session_id")
    val sessionId: UUID,

    @JsonProperty("encrypted_product")
    val encryptedProduct: String
) {

    fun toCommand() = CreateOrder(sessionId, encryptedProduct)
}