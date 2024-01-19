package com.example.book.store.dto;

import java.util.List;

public record BookSearchParameters(List<String> titles, List<String> authors) {
}
