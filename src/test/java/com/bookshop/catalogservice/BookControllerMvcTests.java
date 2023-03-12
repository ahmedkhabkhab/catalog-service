package com.bookshop.catalogservice;

import com.bookshop.catalogservice.config.SecurityConfig;
import com.bookshop.catalogservice.domain.BookNotFoundException;
import com.bookshop.catalogservice.domain.BookService;
import com.bookshop.catalogservice.web.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private JwtDecoder jwtDecoder;

    @Test
    public void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "123";
        given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class);
        mockMvc.perform(get("/book/" + isbn)).andExpect(status().isNotFound());
    }

    @Test
    public void whenDeleteBookWithEmployeeRoleThenShouldReturn204() throws Exception {
        var isbn = "123";
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/books/" + isbn)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                                .authorities(new SimpleGrantedAuthority("ROLE_employee"))))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void whenDeleteBookWithCustomerRoleThenShouldReturn403() throws Exception {
        var isbn = "123";
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/books/" + isbn)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                                .authorities(new SimpleGrantedAuthority("ROLE_customer"))))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void whenDeleteBookNotAuthenticatedThenShouldReturn401() throws Exception {
        var isbn = "123";
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/books/" + isbn))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}
