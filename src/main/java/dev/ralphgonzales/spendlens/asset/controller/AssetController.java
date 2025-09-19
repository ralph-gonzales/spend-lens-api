package dev.ralphgonzales.spendlens.asset.controller;

import dev.ralphgonzales.spendlens.asset.dto.AssetRequestDto;
import dev.ralphgonzales.spendlens.asset.dto.AssetResponseDto;
import dev.ralphgonzales.spendlens.asset.service.AssetService;
import dev.ralphgonzales.spendlens.shared.dto.ApiResponse;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import dev.ralphgonzales.spendlens.shared.enums.CommonSuccessCode;
import dev.ralphgonzales.spendlens.shared.i18n.MessageResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    private final AssetService assetService;
    private final MessageResolver messageResolver;

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedResponse<AssetResponseDto>>> findAll(Pageable pageable) {
        CommonSuccessCode successCode = CommonSuccessCode.RESOURCE_FETCHED;
        PaginatedResponse<AssetResponseDto> paginated = assetService.findAll(pageable);
        ApiResponse<PaginatedResponse<AssetResponseDto>> response = new ApiResponse<>(
                successCode.getCode(),
                messageResolver.getMessage(successCode.getMessageKey()),
                paginated);

        return ResponseEntity.status(successCode.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AssetResponseDto>> create(@RequestBody AssetRequestDto request){
        CommonSuccessCode successCode = CommonSuccessCode.RESOURCE_CREATED;
        AssetResponseDto saved = assetService.create(request);
        ApiResponse<AssetResponseDto> response = new ApiResponse<>(
                successCode.getCode(),
                messageResolver.getMessage(successCode.getMessageKey()),
                saved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetResponseDto>> update(@RequestBody AssetRequestDto request, @PathVariable Long id){
        CommonSuccessCode successCode = CommonSuccessCode.RESOURCE_UPDATED;
        AssetResponseDto saved = assetService.update(request,id);
        ApiResponse<AssetResponseDto> response = new ApiResponse<>(
                successCode.getCode(),
                messageResolver.getMessage(successCode.getMessageKey()),
                saved
        );

        return ResponseEntity.status(successCode.getStatus()).body(response);
    }
}
