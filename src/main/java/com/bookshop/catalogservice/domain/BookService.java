package com.bookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.existByIsbn(book.isbn())) {
            throw new BookAlreadyExistException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        Function<Book, Book> updateExistingBook = oldBook -> {
            var newBook = new Book(oldBook.isbn(), book.title(), book.author(), book.price());
            return bookRepository.save(newBook);
        };

        return bookRepository.findByIsbn(isbn)
                .map(updateExistingBook)
                .orElseGet(() -> addBookToCatalog(book));
    }
}
