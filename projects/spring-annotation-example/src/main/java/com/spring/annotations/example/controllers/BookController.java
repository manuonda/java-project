package com.spring.annotations.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    

    public record Book(Integer id, String name, String description){}
    
}