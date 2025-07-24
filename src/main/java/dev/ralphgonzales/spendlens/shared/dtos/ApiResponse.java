package dev.ralphgonzales.spendlens.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> implements Serializable {
    private int statusCode;
    private String statusMessage;
    private T data;
}
