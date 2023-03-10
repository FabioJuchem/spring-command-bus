package com.mkacunha.springcommandbus.reactor.infraestructure.command

interface CommandHandler<T : Command<R>, R> {

    fun execute(command: T): R
}