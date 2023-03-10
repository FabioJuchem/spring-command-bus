package com.mkacunha.springcommandbus.reactor.domain.session.command

import com.mkacunha.springcommandbus.reactor.domain.session.repository.SessionRepository
import com.mkacunha.springcommandbus.reactor.infraestructure.command.Command
import com.mkacunha.springcommandbus.reactor.infraestructure.command.CommandHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

data class FindPublicKey(val sessionId: UUID) : Command<Mono<UUID>>

@Component
class FindPublicKeyHandler(
    private val sessionRepository: SessionRepository
) : CommandHandler<FindPublicKey, Mono<UUID>> {

    override fun execute(command: FindPublicKey): Mono<UUID> {
        return sessionRepository
            .find(command.sessionId)
            .map { it.key.publicKey }
    }
}