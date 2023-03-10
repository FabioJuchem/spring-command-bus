package com.mkacunha.springcommandbus.reactor.domain.session

import java.util.*

class Session(
    val id: UUID,
    val key: SessionKey,
    val ttl: Long
)