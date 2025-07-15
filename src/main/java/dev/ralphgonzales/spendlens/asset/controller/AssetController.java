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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    private final AssetService assetService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<AssetDto>>> findAll(Pageable pageable) {
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), assetService.findAll(pageable)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaginatedResponse<ApiResponse<AssetDto>>> save(@RequestBody AssetDto assetDto) {
        return null;
    }
}
