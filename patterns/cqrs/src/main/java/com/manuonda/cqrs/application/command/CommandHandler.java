package com.manuonda.cqrs.application.command;

/**
 * Los comando son ejecutados por manejadores por comandos
 * @param <C>
 * @param <R>
 */
public interface CommandHandler <C extends Command<R>, R> {
    R handle(C command);
}
