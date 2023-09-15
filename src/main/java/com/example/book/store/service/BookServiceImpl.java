package com.example.book.store.service;

import com.example.book.store.dto.BookDto;
import com.example.book.store.dto.CreateBookRequestDto;
import com.example.book.store.exception.EntityNotFoundException;
import com.example.book.store.mapper.BookMapper;
import com.example.book.store.model.Book;
import com.example.book.store.repository.BookRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        Book book = bookMapper.toModel(bookRequestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book by id " + id + " not found")
        ));
    }
}
