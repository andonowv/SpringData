package com.example.demo.service.Impl;

import com.example.demo.model.entity.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (authorRepository.count()>0){
            return;
        }
        Files.readAllLines(Path.of("src/main/resources/files/authors.txt"))
                .forEach(row -> {
                    String [] fullName = row.split("\\s+");
                    Author author = new Author(fullName[0], fullName[1]);

                    authorRepository.save(author);
                });
    }

    @Override
    public Author getRandomAuthor() {

        long randomId = ThreadLocalRandom.current().nextLong(1, 31);
        return authorRepository.findById(randomId).orElse(null);
    }
}
