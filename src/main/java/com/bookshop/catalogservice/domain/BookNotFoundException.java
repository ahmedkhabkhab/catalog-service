package com.bookshop.catalogservice.domain;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String isbn) {
        super("Book not found: " + isbn);
    }
}
