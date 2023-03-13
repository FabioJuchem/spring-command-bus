package com.mkacunha.springcommandbus.reactor.controller

import com.mkacunha.springcommandbus.reactor.controller.request.CreateOrderRequest
import com.mkacunha.springcommandbus.reactor.controller.response.OrderCreatedResponse
import com.mkacunha.springcommandbus.reactor.infraestructure.command.CommandBus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class OrderController(
    private val commandBus: CommandBus
) {

    @PostMapping("/orders")
    @ResponseStatus(CREATED)
    fun createOrder(@RequestBody request: CreateOrderRequest): Mono<OrderCreatedResponse> =
        commandBus.execute(request.toCommand())
            .map { OrderCreatedResponse(it.id) }
}