package com.mkacunha.springcommandbus.reactor.infraestructure.command

interface CompositeCommandHandler<T : CompositeCommand<C, R>, C, R> {

    fun execute(composition: C, command: T): R
}