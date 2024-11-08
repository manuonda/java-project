package com.spring.annotations.example.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@Controller
//@ResponseBody
@RestController
@RequestMapping("/api")
public class BookController {

    @RequestMapping("/hello-world")
    //@ResponseBody
    public String hello() {
        return "Hello World";
    }

    //@RequestMapping("/book")
    //@ResponseBody
    @GetMapping(value = {"/books","/java"})
    public Book getBook() {
        return new Book(1, "Title1", "Description one");
    }
    

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping(value = "/books/create" ,
    consumes= MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        System.out.println(book.id());
        System.out.println(book.name());
        System.out.println(book.description());
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
       // return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    

    public record Book(Integer id, String name, String description){}
    
}