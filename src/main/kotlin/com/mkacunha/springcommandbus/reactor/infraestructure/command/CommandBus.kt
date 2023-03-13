package com.mkacunha.springcommandbus.reactor.infraestructure.command

import org.springframework.stereotype.Component
import java.lang.reflect.ParameterizedType

@Component
class CommandBus(
    private val handlers: List<CommandHandler<*, *>>,
    private val compositeHandlers: List<CompositeCommandHandler<*, *, *>>
) {

    fun <R> execute(command: Command<R>): R = getHandler(command).execute(command)

    fun <C, R> execute(command: CompositeCommand<C, R>): R {
        val handler = getHandler(command)
        val composition = command.composition()

        if (composition is CompositeCommand<*, *>) {
            return handler.execute(execute(composition) as C, command)
        }

        return handler.execute(execute(composition), command)
    }

    private fun <T : Command<R>, R> getHandler(command: T): CommandHandler<T, R> =
        handlers.filter { isHandler(it, command) }[0] as CommandHandler<T, R>

    private fun <R, T : Command<R>> isHandler(handler: CommandHandler<*, *>, command: T) =
        (handler.javaClass.genericInterfaces[0] as ParameterizedType).actualTypeArguments[0].typeName == command::class.java.typeName

    private fun <R, T : Command<R>> isCompositeHandler(handler: CompositeCommandHandler<*, *, *>, command: T) =
        (handler.javaClass.genericInterfaces[0] as ParameterizedType).actualTypeArguments[0].typeName == command::class.java.typeName

    private fun <T : CompositeCommand<C, R>, C, R> getHandler(command: T): CompositeCommandHandler<T, C, R> =
        compositeHandlers.filter { isCompositeHandler(it, command) }[0] as CompositeCommandHandler<T, C, R>
}