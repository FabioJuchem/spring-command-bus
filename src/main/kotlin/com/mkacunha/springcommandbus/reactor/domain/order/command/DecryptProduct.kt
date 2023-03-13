package com.mkacunha.springcommandbus.reactor.domain.order.command

import com.mkacunha.springcommandbus.reactor.domain.order.Product
import com.mkacunha.springcommandbus.reactor.domain.session.Session
import com.mkacunha.springcommandbus.reactor.domain.session.command.FindSessionById
import com.mkacunha.springcommandbus.reactor.infraestructure.command.Command
import com.mkacunha.springcommandbus.reactor.infraestructure.command.CompositeCommand
import com.mkacunha.springcommandbus.reactor.infraestructure.command.CompositeCommandHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

class DecryptProduct(
    private val sessionId: UUID,
    val encryptedProduct: String
) : CompositeCommand<Mono<Session>, Mono<Product>> {
    override fun composition(): Command<Mono<Session>> = FindSessionById(sessionId)
}

@Component
class DecryptProductHandler : CompositeCommandHandler<DecryptProduct, Mono<Session>, Mono<Product>> {
    override fun execute(composition: Mono<Session>, command: DecryptProduct): Mono<Product> =
        composition
            .map { it.key.privateKey }
            .map {
                val product = Product("123456", "Product One")
                println("${DecryptProductHandler::class.simpleName} -> product ${product.number} decrypted with private key: $it")
                product
            }

}