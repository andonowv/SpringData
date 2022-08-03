package com.example.demo.service;

import com.example.demo.model.entity.Author;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();
}
