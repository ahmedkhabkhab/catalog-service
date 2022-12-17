package com.bookshop.catalogservice.demo;

import com.bookshop.catalogservice.domain.Book;
import com.bookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Profile("testdata")
@Component
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBooksTestData() {
        var book1 = new Book("1", "Title 1", "Author 1", 10.0);
        bookRepository.save(book1);

        var book2 = new Book("2", "Title 2", "Author 2", 20.0);
        bookRepository.save(book2);

        var book3 = new Book("3", "Title 3", "Author 3", 30.0);
        bookRepository.save(book3);

        var book4 = new Book("4", "Title 4", "Author 4", 40.0);
        bookRepository.save(book4);
    }
}
