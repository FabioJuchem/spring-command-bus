package com.mkacunha.springcommandbus.reactor.controller

import com.mkacunha.springcommandbus.reactor.controller.request.CreateSessionRequest
import com.mkacunha.springcommandbus.reactor.controller.response.CreatedSessionResponse
import com.mkacunha.springcommandbus.reactor.controller.response.PublicKeyFoundResponse
import com.mkacunha.springcommandbus.reactor.controller.response.SessionFoundResponse
import com.mkacunha.springcommandbus.reactor.domain.session.command.FindPublicKey
import com.mkacunha.springcommandbus.reactor.domain.session.command.FindSessionById
import com.mkacunha.springcommandbus.reactor.infraestructure.command.CommandBus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.*

@RestController
class SessionController(
    private val commandBus: CommandBus
) {

    @PostMapping("/sessions")
    fun createSession(@RequestBody request: CreateSessionRequest): Mono<CreatedSessionResponse> =
        commandBus
            .execute(request.toCommand())
            .map { CreatedSessionResponse(it) }

    @GetMapping("/sessions")
    fun findSession(@RequestHeader("x-session-id") sessionId: UUID): Mono<SessionFoundResponse> =
        commandBus
            .execute(FindSessionById(sessionId))
            .map { SessionFoundResponse(it) }

    @GetMapping("/sessions/publickey")
    fun findPublicKey(@RequestHeader("x-session-id") sessionId: UUID): Mono<PublicKeyFoundResponse> =
        commandBus
            .execute(FindPublicKey(sessionId))
            .map { PublicKeyFoundResponse(it) }
}