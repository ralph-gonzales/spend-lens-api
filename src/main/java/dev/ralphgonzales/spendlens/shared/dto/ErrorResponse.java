package dev.ralphgonzales.spendlens.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorResponse(
        int status,
        String path,
        Instant timestamp,
        String code,
        String message,
        List<ApiFieldError> errors
) { }
