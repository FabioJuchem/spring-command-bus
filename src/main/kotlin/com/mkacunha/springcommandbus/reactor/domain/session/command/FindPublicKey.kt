package com.mkacunha.springcommandbus.reactor.domain.session.command

import com.mkacunha.springcommandbus.reactor.domain.session.Session
import com.mkacunha.springcommandbus.reactor.infraestructure.command.Command
import com.mkacunha.springcommandbus.reactor.infraestructure.command.CompositeCommand
import com.mkacunha.springcommandbus.reactor.infraestructure.command.CompositeCommandHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

data class FindPublicKey(val sessionId: UUID) : CompositeCommand<Mono<Session>, Mono<UUID>> {

    override fun composition(): Command<Mono<Session>> = FindSessionById(sessionId)
}

@Component
class FindPublicKeyHandler : CompositeCommandHandler<FindPublicKey, Mono<Session>, Mono<UUID>> {

    override fun execute(composition: Mono<Session>, command: FindPublicKey): Mono<UUID> =
        composition.map { it.key.publicKey }
}