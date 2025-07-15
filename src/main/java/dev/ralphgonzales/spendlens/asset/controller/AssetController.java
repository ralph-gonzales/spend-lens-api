package dev.ralphgonzales.spendlens.asset.controller;

import dev.ralphgonzales.spendlens.asset.dto.AssetDto;
import dev.ralphgonzales.spendlens.shared.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    @GetMapping("/")
    public ResponseEntity<ApiResponse<AssetDto>> findAll() {
        return null;
    }
}
