package com.bookshop.catalogservice.domain;

public class BookAlreadyExistException extends RuntimeException {

    public BookAlreadyExistException(String isbn) {
        super("Book already exist: " + isbn);
    }
}
