package com.spring.annotations.example.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




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
    

    // @RequestMapping(value = "/books/update/{id}", 
    //  method=RequestMethod.PUT,
    //  produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping("/api/books/update/{id}")
    public ResponseEntity<Book> updateBookEntity(@RequestBody Book book, @PathVariable("id") Integer id) {
        System.out.println(book.id());
        System.out.println(book.name());
        System.out.println(book.description());
        
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }
    
    // @RequestMapping(value = "/books/update/{id}", 
    //  method=RequestMethod.DELETE)
    @DeleteMapping("/api/books/update/{id}")
    public ResponseEntity<String> deleteBookEntity(@PathVariable("id") Integer id) {
        System.out.println("id : {}".formatted(id));
       return ResponseEntity.status(HttpStatus.OK).body("Book delete successfully")
    }


    @GetMapping("/books/{id}/{title}/{description}")
    public void pathVariableDemo(@PathVariable("id")  Integer id,
                                 @PathVariable("title") String title,
                                 @PathVariable("description") String description) {
        System.out.println("id : {}".formatted(id));
        System.out.println("title : {}".formatted(title));
        System.out.println("description : {}".formatted(description));

    }


    //http://localhost:8080/api/books/query?id=1&title=Core Java
    @GetMapping("/books/query")
    public void requestParam(@RequestParam("id") Integer id, 
                             @RequestParam("title") String title) {
        System.out.println("id : {}".formatted(id));
        System.out.println("title : {}".formatted(title));
    }
    
    
    

    public record Book(Integer id, String name, String description){}
    
}