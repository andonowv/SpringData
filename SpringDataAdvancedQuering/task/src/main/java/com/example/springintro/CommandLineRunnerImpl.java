package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        //printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
     //   printAllAuthorsAndNumberOfTheirBooks();
        // pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

        System.out.println("Please, select exercise");

        int exNum = Integer.parseInt(bufferedReader.readLine());

        switch (exNum){
            case 1 -> booksTitleByAgeRestriction();
            case 2 -> goldenBook();
            case 3 -> booksByPrise();
            case 4 -> booksNotReleasedInAGivenYear();
            case 5 -> booksReleasedBeforeDate();
            case 6 -> authorSearch();
            case 7 -> bookSearch();
        }
    }

    private void bookSearch() throws IOException {
        System.out.println("Please, enter key for searching a book");
        String input = bufferedReader.readLine().toLowerCase();
        bookService.findBookThatContainsAKey(input)
                .forEach(System.out::println);
    }

    private void authorSearch() throws IOException {
        System.out.println("Enter first name with str:");
        String endStr = bufferedReader.readLine();

        authorService.findAuthorsFirstNameThatEndWithStr(endStr)
                .forEach(System.out::println);
    }

    private void booksReleasedBeforeDate() throws IOException {
        System.out.println("Enter a date in format dd-MM-yyyy");
        LocalDate date = LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        bookService.findAllBooksThatAreReleasedBeforeDate(date)
                .forEach(System.out::println);
    }

    private void booksNotReleasedInAGivenYear() throws IOException {
        System.out.println("Enter Year");
        int year = Integer.parseInt(bufferedReader.readLine());
        bookService.findAllBooksThatAreNotReleasedInAGivenYear(year)
                .forEach(System.out::println);
    }

    private void booksByPrise() {
        bookService.findAllBookTitlesWithPriceLessThan5AndOrMoreThan40()
                .forEach(System.out::println);
    }

    private void goldenBook() {
        bookService.findAllGoldenBookWithLessThan5000copies()
                .forEach(System.out::println);
    }

    private void booksTitleByAgeRestriction() throws IOException {
        System.out.println("Enter age restriction");
        AgeRestriction ageRestriction = AgeRestriction.valueOf(bufferedReader.readLine().toUpperCase());
        bookService.findAllBookTitlesByAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
