package com.bookshop.catalogservice.web;

import com.bookshop.catalogservice.domain.BookAlreadyExistException;
import com.bookshop.catalogservice.domain.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String bookNotFoundHandler(BookNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(BookAlreadyExistException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String bookAlreadyExistHandler(BookAlreadyExistException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach(err -> {
            errors.put(((FieldError) err).getField(), err.getDefaultMessage());
        });
        return errors;
    }

}
