package dev.ralphgonzales.spendlens.asset.controller;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.asset.service.AssetService;
import dev.ralphgonzales.spendlens.shared.dto.ApiResponse;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import dev.ralphgonzales.spendlens.shared.enums.CommonSuccessCode;
import dev.ralphgonzales.spendlens.shared.validation.group.ValidationGroups;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    private final AssetService assetService;
    private final MessageSource messageSource;

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedResponse<AssetDto>>> findAll(Pageable pageable) {
        PaginatedResponse<AssetDto> paginated = assetService.findAll(pageable);
        ApiResponse<PaginatedResponse<AssetDto>> response = new ApiResponse<>(
                CommonSuccessCode.RESOURCE_FETCHED.getCode(),
                messageSource.getMessage(CommonSuccessCode.RESOURCE_FETCHED.getMessageKey(),null, LocaleContextHolder.getLocale()),
                paginated
        );

        return ResponseEntity.status(CommonSuccessCode.RESOURCE_FETCHED.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<PaginatedResponse<ApiResponse<AssetDto>>> save(@Validated(ValidationGroups.Create.class)
                                                                             @RequestBody AssetDto assetDto) {

        // TODO: add buildAndExpand value
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand("")
                .toUri();

        return null;
    }
}
