package com.mkacunha.springcommandbus.reactor.domain.session.command

import com.mkacunha.springcommandbus.reactor.domain.session.Session
import com.mkacunha.springcommandbus.reactor.domain.session.SessionKey
import com.mkacunha.springcommandbus.reactor.domain.session.repository.SessionRepository
import com.mkacunha.springcommandbus.reactor.infraestructure.command.Command
import com.mkacunha.springcommandbus.reactor.infraestructure.command.CommandHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

data class CreateSession(val ttl: Long) : Command<Mono<Session>>

@Component
class CreateSessionHandler(
    private val sessionRepository: SessionRepository
) : CommandHandler<CreateSession, Mono<Session>> {

    override fun execute(command: CreateSession): Mono<Session> {
        val key = SessionKey(privateKey = UUID.randomUUID(), publicKey = UUID.randomUUID())
        val session = Session(id = UUID.randomUUID(), key = key, ttl = command.ttl)
        return sessionRepository.save(session)
    }
}