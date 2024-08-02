package com.urunsiyabend.flightscqrs.cq.command.common;

public interface CommandHandler<TCommand extends Command> {
    CommandResult handle(TCommand command) throws Exception;
}
