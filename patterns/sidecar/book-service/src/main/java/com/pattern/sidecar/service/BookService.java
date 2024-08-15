package com.pattern.sidecar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pattern.sidecar.dto.Book;

@Service
public class BookService {
  
    private List<Book> books = new ArrayList<>();

    public Book addBook(Book book){
        books.add(book);
        return book;
    }

    public List<Book> getBooks(){
        return books;
    }
}
