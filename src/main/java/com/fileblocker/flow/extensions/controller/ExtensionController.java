package com.fileblocker.flow.extensions.controller;

import com.fileblocker.flow.common.dto.ApiResponse;
import com.fileblocker.flow.extensions.domain.CustomExtension;
import com.fileblocker.flow.extensions.domain.FixedExtension;
import com.fileblocker.flow.extensions.dto.CreateCustomExtensionRequest;
import com.fileblocker.flow.extensions.dto.ToggleFixedExtensionRequest;
import com.fileblocker.flow.extensions.service.ExtensionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/extensions")
@RequiredArgsConstructor
public class ExtensionController {

    private final ExtensionService service;

    @GetMapping("/fixed")
    public ApiResponse<List<FixedExtension>> fixed(){ return ApiResponse.ok(service.listFixed()); }

    @PatchMapping("/fixed/toggle")
    public ApiResponse<FixedExtension> toggle(@Valid @RequestBody ToggleFixedExtensionRequest req){
        return ApiResponse.ok(service.toggleFixed(req));
    }

    @GetMapping("/custom")
    public ApiResponse<List<CustomExtension>> custom(){ return ApiResponse.ok(service.listCustom()); }

    @PostMapping("/custom")
    public ApiResponse<CustomExtension> add(@Valid @RequestBody CreateCustomExtensionRequest req){
        return ApiResponse.ok(service.addCustom(req));
    }

    @DeleteMapping("/custom/{id}")
    public ApiResponse<Void> del(@PathVariable Long id){
        service.removeCustom(id);
        return ApiResponse.ok(null);
    }

    @GetMapping("/blocked")
    public ApiResponse<List<String>> blocked(){ return ApiResponse.ok(service.getAllBlocked()); }
}

