package com.pattern.sidecar.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pattern.sidecar.dto.Book;
import com.pattern.sidecar.service.BookService;

import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    
}
