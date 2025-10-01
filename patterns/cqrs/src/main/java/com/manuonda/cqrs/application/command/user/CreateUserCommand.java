package com.manuonda.cqrs.application.command.user;

import com.manuonda.cqrs.application.command.Command;

/**
 * La interfaz especifica que este comando devolvera un valor
 * Long una vez que se haya ejecutado
 * @param name
 * @param email
 */
public record CreateUserCommand(String name, String email) implements Command<Long> {
}
