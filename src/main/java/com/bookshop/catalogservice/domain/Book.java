package com.bookshop.catalogservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record Book (
        @NotBlank(message = "The ISBN cannot be defined")
        String isbn,

        @NotBlank(message = "The title cannot be defined")
        String title,

        @NotBlank(message = "The author cannot be defined")
        String author,

        @NotNull
        @Positive(message = "The price should be positive")
        Double price
){}
