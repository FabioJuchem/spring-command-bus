package com.mkacunha.springcommandbus.reactor.domain.session.command

import com.mkacunha.springcommandbus.reactor.domain.session.Session
import com.mkacunha.springcommandbus.reactor.domain.session.repository.SessionRepository
import com.mkacunha.springcommandbus.reactor.infraestructure.command.Command
import com.mkacunha.springcommandbus.reactor.infraestructure.command.CommandHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.UUID

data class FindSessionById(val id: UUID): Command<Mono<Session>>

@Component
class FindSessionByIdHandler(
    private val sessionRepository: SessionRepository
): CommandHandler<FindSessionById, Mono<Session>> {

    override fun execute(command: FindSessionById): Mono<Session> =
        sessionRepository.find(command.id)
}