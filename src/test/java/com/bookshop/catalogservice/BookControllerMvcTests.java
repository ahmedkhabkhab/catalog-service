package com.bookshop.catalogservice;

import com.bookshop.catalogservice.domain.BookNotFoundException;
import com.bookshop.catalogservice.domain.BookService;
import com.bookshop.catalogservice.web.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(BookController.class)
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "123";
        given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class);
        mockMvc.perform(get("/book/" + isbn)).andExpect(status().isNotFound());
    }

}
