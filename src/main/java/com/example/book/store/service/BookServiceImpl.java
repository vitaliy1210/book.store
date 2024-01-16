package com.example.book.store.service;

import com.example.book.store.dto.BookDto;
import com.example.book.store.dto.CreateBookRequestDto;
import com.example.book.store.exception.EntityNotFoundException;
import com.example.book.store.mapper.BookMapper;
import com.example.book.store.model.Book;
import com.example.book.store.repository.BookRepository;
import com.example.book.store.repository.SpecificationProvider;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final SpecificationProvider<Book> bookSpecificationProvider;

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

    @Override
    public BookDto update(Long id, CreateBookRequestDto bookRequestDto) {
        Book book = bookMapper.toModel(bookRequestDto);
        book.setId(id);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> search(Map<String, List<String>> params) {
        Specification<Book> specification = null;
        for (Map.Entry<String, List<String>> entry: params.entrySet()) {
            Specification<Book> sp = bookSpecificationProvider
                     .getSpecification(entry.getKey(), entry.getValue());
            specification = specification == null ? Specification.where(sp) :
                    specification.and(sp);
        }
        return bookRepository.findAll(specification)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}
