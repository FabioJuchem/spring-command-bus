package com.mkacunha.springcommandbus.reactor.infraestructure.command

import org.springframework.stereotype.Component
import java.lang.reflect.ParameterizedType

@Component
class CommandBus(
    private val handlers: List<CommandHandler<*, *>>
) {

    fun <R> execute(query: Command<R>): R = getHandler(query).execute(query)

    private fun <T : Command<R>, R> getHandler(query: T): CommandHandler<T, R> =
        handlers.filter { isHandler(it, query) }[0] as CommandHandler<T, R>


    private fun <R, T : Command<R>> isHandler(handler: CommandHandler<*, *>, query: T) =
        (handler.javaClass.genericInterfaces[0] as ParameterizedType).actualTypeArguments[0].typeName == query::class.java.typeName

}