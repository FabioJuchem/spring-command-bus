package com.mkacunha.springcommandbus.reactor.controller.request

import com.mkacunha.springcommandbus.reactor.domain.session.command.CreateSession

class CreateSessionRequest(val ttl: Long) {

    fun toCommand() = CreateSession(this.ttl)
}