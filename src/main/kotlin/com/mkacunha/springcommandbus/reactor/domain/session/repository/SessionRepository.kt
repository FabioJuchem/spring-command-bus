package com.mkacunha.springcommandbus.reactor.domain.session.repository

import com.mkacunha.springcommandbus.reactor.domain.session.Session
import com.mkacunha.springcommandbus.reactor.domain.session.SessionKey
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@Repository
class SessionRepository {

    fun save(session: Session) = session.toMono()

    fun find(id: UUID): Mono<Session> =
        Session(id = id, key = SessionKey(UUID.randomUUID(), UUID.randomUUID()), ttl = 1000)
            .toMono()
}