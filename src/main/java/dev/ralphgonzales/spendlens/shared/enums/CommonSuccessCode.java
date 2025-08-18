package dev.ralphgonzales.spendlens.shared.enums;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum CommonSuccessCode implements BaseResponseCode{
    RESOURCE_FETCHED("SUC_200_COMMON_001","success.resource.fetch", HttpStatus.OK),
    RESOURCE_CREATED("SUC_201_COMMON_001", "success.resource.create", HttpStatus.CREATED),
    RESOURCE_UPDATED("SUC_200_COMMON_002", "success.resource.update", HttpStatus.OK),
    RESOURCE_DELETED("SUC_200_COMMON_003", "success.resource.delete", HttpStatus.OK);

    private final String code;
    private final String messageKey;
    private final HttpStatus status;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessageKey() {
        return messageKey;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
