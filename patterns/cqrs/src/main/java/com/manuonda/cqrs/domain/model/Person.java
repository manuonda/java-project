package com.manuonda.cqrs.domain.model;

import com.manuonda.cqrs.domain.exception.PersonAlreadyExistsException;

public class Person {

   private Long id;
   private String name;
   private String email;

   public Person(String name, String email) {

       this.name = name;
       this.email = email;
   }

    public Person(Long id, String name, String email) {
        this(name, email);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void changeName(String newName){
       validateName(newName);
       this.name = newName;
    }
    public void changeEmail(String newEmail) {
       validateEmail(newEmail);
       this.email = newEmail;
    }

    private void validateName(String name) {
       if(name == null || name.trim().isEmpty())
           throw new IllegalArgumentException("Name cannot be null or empty");
    }

    private void validateEmail(String email) {
       if(email == null || email.trim().isEmpty())
           throw new IllegalArgumentException("Email cannot be null or empty");

       if(!email.contains("@")){
           throw new IllegalArgumentException("Invalid email format");
       }
    }

}
