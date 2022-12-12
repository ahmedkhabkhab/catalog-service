package com.bookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    public BookService() {

    }

    public Iterable<Book> viewBookList() {
        return null;
    }

    public Book viewBookDetails(String isbn) {
        return null;
    }

    public Book addBookToCatalog(Book book) {
        return null;
    }

    public void removeBookFromCatalog(String isbn) {

    }

    public Book editBookDetails(String isbn, Book book) {
        return null;
    }

}
