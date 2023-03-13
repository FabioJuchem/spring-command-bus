package com.mkacunha.springcommandbus.reactor.infraestructure.command

interface CompositeCommand<C, R> : Command<R> {
    fun composition(): Command<C>

}