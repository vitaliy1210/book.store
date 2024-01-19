package com.example.book.store.repository;

import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public abstract class AbstractSpecificationProvider<T> implements SpecificationProvider<T> {
    public Specification<T> getSpecification(List<String> params) {
        return (root, query, criteriaBuilder) ->
                root.get(getKey()).in(params);
    }
}
