package com.manuonda.cqrs.application.command.user;


import com.manuonda.cqrs.application.command.CommandHandler;
import com.manuonda.cqrs.domain.repository.PersonRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, Long> {

    private final PersonRepository userRepository;

    public CreateUserCommandHandler(PersonRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Long handle(CreateUserCommand command) {
        return 0L;
    }
}
