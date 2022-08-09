package com.example.demo.service;

import com.example.demo.model.entity.Book;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface BookService {
    void seedBooks() throws IOException;

    List <Book> findAllBooksAfterYear(int year);
}
