package com.manuonda.cqrs.domain.repository;

import com.manuonda.cqrs.domain.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    Person save(Person persona);
    Optional<Person> findById(Long id);
    Optional<Person> findByEmail(String email);
    List<Person> findAll();
    void delete(Long gid);
}
