package dev.ralphgonzales.spendlens.shared.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ApiResponse<T> implements Serializable {
    private int statusCode;
    private String statusMessage;
    private T data;
}
