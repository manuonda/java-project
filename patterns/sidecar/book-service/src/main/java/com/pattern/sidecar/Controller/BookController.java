package com.pattern.sidecar.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pattern.sidecar.dto.Book;
import com.pattern.sidecar.service.BookService;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/books")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @SneakyThrows  //the exception maneja lombok
    public ResponseEntity<Book> addBook (@RequestBody Book book)  {
        var savedBook = bookService.addBook(book);
        log.info("BookController::addBook request {}", new ObjectMapper().writeValueAsString(book));
         return ResponseEntity.status(HttpStatus.OK).body(savedBook);
    }
    

    @GetMapping
    @SneakyThrows
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getBooks();
        log.info("BookControlle::getBooks response {}", new ObjectMapper().writeValueAsString(books));
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    
}
