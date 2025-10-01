package com.manuonda.cqrs.infraestructure.config;


import com.manuonda.cqrs.domain.PersonDomainService;
import com.manuonda.cqrs.domain.repository.PersonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

   @Bean
    public PersonDomainService personDomainService(
            PersonRepository personRepository
   ) {
       return new PersonDomainService(personRepository);
   }
}
