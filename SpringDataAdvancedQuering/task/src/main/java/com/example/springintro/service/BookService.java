package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List <String> findAllBookTitlesByAgeRestriction(AgeRestriction ageRestriction);

    List <String> findAllGoldenBookWithLessThan5000copies();

    List <String> findAllBookTitlesWithPriceLessThan5AndOrMoreThan40();

    List <String> findAllBooksThatAreNotReleasedInAGivenYear(int year);

    List <String> findAllBooksThatAreReleasedBeforeDate(LocalDate date);

    List <String> findBookThatContainsAKey(String input);
}
