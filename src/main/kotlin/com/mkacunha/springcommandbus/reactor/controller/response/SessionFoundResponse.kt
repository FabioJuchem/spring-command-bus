package com.mkacunha.springcommandbus.reactor.controller.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.mkacunha.springcommandbus.reactor.domain.session.Session
import java.util.*

@JsonRootName("session")
class SessionFoundResponse(
    @JsonProperty("session_id")
    val id: UUID
) {
    constructor(session: Session) : this(session.id)
}