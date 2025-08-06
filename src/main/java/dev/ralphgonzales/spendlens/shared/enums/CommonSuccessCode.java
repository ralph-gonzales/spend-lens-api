package dev.ralphgonzales.spendlens.shared.enums;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum CommonSuccessCode implements BaseResponseCode{
    RESOURCE_FETCHED("RESOURCE_FETCHED","success.resource.fetch", HttpStatus.OK),
    RESOURCE_CREATED("RESOURCE_CREATED", "success.resource.create", HttpStatus.CREATED),
    RESOURCE_UPDATED("RESOURCE_UPDATED", "success.resource.update", HttpStatus.OK),
    RESOURCE_DELETED("RESOURCE_DELETED", "success.resource.delete", HttpStatus.OK);

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
