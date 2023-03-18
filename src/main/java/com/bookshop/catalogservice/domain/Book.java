package com.bookshop.catalogservice.domain;

import org.springframework.data.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Instant;

public record Book (

        @Id
        Long id,
        @NotBlank(message = "The ISBN cannot be defined")
        String isbn,

        @NotBlank(message = "The title cannot be defined")
        String title,

        @NotBlank(message = "The author cannot be defined")
        String author,

        @NotNull
        @Positive(message = "The price should be positive")
        Double price,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate,

        @CreatedBy
        String createdBy,

        @LastModifiedBy
        String lastModifiedBy,

        @Version
        int version
){
        public static Book of(String isbn, String title, String author, Double price) {
                return new Book(null, isbn, title, author, price, null, null, null, null, 0);
        }
}
