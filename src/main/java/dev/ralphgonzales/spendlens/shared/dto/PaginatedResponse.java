package dev.ralphgonzales.spendlens.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record PaginatedResponse<T> (
    List<T> data,
    int currentPage,
    int pageSize,
    long totalElements,
    int totalPages,
    boolean isLast
){ }
