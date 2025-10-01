package com.manuonda.cqrs.domain;

import com.manuonda.cqrs.domain.exception.PersonAlreadyExistsException;
import com.manuonda.cqrs.domain.model.Person;
import com.manuonda.cqrs.domain.repository.PersonRepository;

import java.util.List;

public class PersonDomainService {

    private final PersonRepository personRepository;
    public PersonDomainService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(String name, String email) {
        if(personRepository.findByEmail(email).isPresent()){
            throw new PersonAlreadyExistsException(email);
        }

        Person person = new Person(name, email);
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

}
