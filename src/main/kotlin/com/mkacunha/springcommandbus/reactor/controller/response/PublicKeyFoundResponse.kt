package com.mkacunha.springcommandbus.reactor.controller.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import java.util.*

@JsonRootName("session")
class PublicKeyFoundResponse(
    @JsonProperty("public-key")
    publicKey: UUID
)