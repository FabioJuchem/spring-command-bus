package com.mkacunha.springcommandbus.reactor.domain.order.command

import com.mkacunha.springcommandbus.reactor.domain.order.Product
import com.mkacunha.springcommandbus.reactor.domain.order.Order
import com.mkacunha.springcommandbus.reactor.infraestructure.command.Command
import com.mkacunha.springcommandbus.reactor.infraestructure.command.CompositeCommand
import com.mkacunha.springcommandbus.reactor.infraestructure.command.CompositeCommandHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

class CreateOrder(
    private val sessionId: UUID,
    private val encryptedProduct: String
) : CompositeCommand<Mono<Product>, Mono<Order>> {
    override fun composition(): Command<Mono<Product>> = DecryptProduct(sessionId, encryptedProduct)
}

@Component
class CreateOrderHandler : CompositeCommandHandler<CreateOrder, Mono<Product>, Mono<Order>> {

    override fun execute(composition: Mono<Product>, command: CreateOrder): Mono<Order> =
        composition
            .map {
                val order = Order(UUID.randomUUID())
                println("${CreateOrderHandler::class.simpleName} -> Order created: ${order.id}")
                order
            }
}