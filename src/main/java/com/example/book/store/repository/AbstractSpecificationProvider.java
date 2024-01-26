package com.example.book.store.repository;

import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractSpecificationProvider<T> implements SpecificationProvider<T> {
    public Specification<T> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                root.get(getKey()).in(Arrays.stream(params).toArray());
    }
}
