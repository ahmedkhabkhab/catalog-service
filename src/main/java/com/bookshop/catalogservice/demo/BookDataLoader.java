package com.bookshop.catalogservice.demo;

import com.bookshop.catalogservice.domain.Book;
import com.bookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("testdata")
@Component
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBooksTestData() {
        bookRepository.deleteAll();
        var book1 = Book.of("1", "Title 1", "Author 1", 10.0);
        var book2 = Book.of("2", "Title 2", "Author 2", 20.0);
        var book3 = Book.of("3", "Title 3", "Author 3", 30.0);
        var book4 = Book.of("4", "Title 4", "Author 4", 40.0);
        bookRepository.saveAll(List.of(book1, book2, book3, book4));
    }
}
