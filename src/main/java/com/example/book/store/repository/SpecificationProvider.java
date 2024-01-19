package com.example.book.store.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface SpecificationProvider<T> {
    String getKey();

    Specification<T> getSpecification(List<String> params);
}
