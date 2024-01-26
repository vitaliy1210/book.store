package com.example.book.store.repository.book;

import com.example.book.store.exception.NoSuchSpecificationProviderException;
import com.example.book.store.model.Book;
import com.example.book.store.repository.SpecificationProvider;
import com.example.book.store.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders
                .stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new NoSuchSpecificationProviderException("Can't find "
                        + "specification with key: " + key));
    }
}
