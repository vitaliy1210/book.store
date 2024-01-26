package com.example.book.store.repository.book;

import com.example.book.store.model.Book;
import com.example.book.store.repository.AbstractSpecificationProvider;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider extends AbstractSpecificationProvider<Book> {
    public String getKey() {
        return "author";
    }
}
