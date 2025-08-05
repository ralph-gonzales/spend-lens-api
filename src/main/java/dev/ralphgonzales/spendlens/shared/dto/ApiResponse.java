package dev.ralphgonzales.spendlens.shared.dto;

public record ApiResponse<T> (int status, T data){ }
