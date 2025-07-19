package dev.ralphgonzales.spendlens.asset.controller;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.asset.service.AssetService;
import dev.ralphgonzales.spendlens.shared.dto.ApiResponse;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    private final AssetService assetService;

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedResponse<AssetDto>>> findAll(@RequestParam Integer page,
                                                                            @RequestParam Integer size,
                                                                            Pageable pageable) {

        PaginatedResponse<AssetDto> paginated = assetService.findAll(pageable);

        ApiResponse<PaginatedResponse<AssetDto>> response = ApiResponse.<PaginatedResponse<AssetDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .statusMessage(HttpStatus.OK.name())
                .data(paginated)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PaginatedResponse<ApiResponse<AssetDto>>> save(@RequestBody AssetDto assetDto) {
        return null;
    }
}
