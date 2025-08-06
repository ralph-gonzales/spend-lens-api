package dev.ralphgonzales.spendlens.shared.dto;

public record ApiResponse<T> (
        String code,
        String message,
        T data){ }
