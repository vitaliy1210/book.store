package com.example.book.store;

import com.example.book.store.model.Book;
import com.example.book.store.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("Kobzar");
            book.setAuthor("Taras Shevchenko");
            book.setIsbn("aldknvlks2132");
            book.setPrice(BigDecimal.valueOf(900L));
            book.setDescription("A big collection of poems.");
            bookService.save(book);
            System.out.println(bookService.findAll());
        };
    }
}
