package com.example.book.store.service;

import com.example.book.store.dto.BookDto;
import com.example.book.store.dto.CreateBookRequestDto;
import java.util.List;
import java.util.Map;

public interface BookService {
    BookDto save(CreateBookRequestDto bookRequestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    BookDto update(Long id, CreateBookRequestDto bookRequestDto);

    void deleteById(Long id);

    List<BookDto> search(Map<String, List<String>> params);
}
