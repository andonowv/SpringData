package com.example.demo.service.Impl;

import com.example.demo.model.entity.*;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0){
            return;
        }

        Files.readAllLines(Path.of("src/main/resources/files/books.txt"))
                .forEach(row -> {
                    String [] fullData = row.split("\\s+");

                    Book book = createBookInfo(fullData);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository.findAllByReleaseDateAfter(LocalDate.of(year, 12 ,31));
    }

    private Book createBookInfo(String[] fullData) {
        EditionType editionType = EditionType.values()[Integer.parseInt(fullData[0])];
        LocalDate releaseDate = LocalDate.parse(fullData[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(fullData[2]);
        BigDecimal price = new BigDecimal(fullData[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(fullData[4])];
        String title = Arrays.stream(fullData)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set <Category> categories = categoryService.getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);
    }
}
