package com.fileblocker.flow.file.controller;


import com.fileblocker.flow.common.dto.ApiResponse;
import com.fileblocker.flow.file.dto.UploadValidateRequest;
import com.fileblocker.flow.file.service.FileValidationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/file")
@RequiredArgsConstructor
public class FileController {

    private final FileValidationService validationService;

    @GetMapping("health")
    public String healthCheck() {
        return "server ok";
    }

    /** 파일명만으로 차단 검사 */
    @PostMapping("/validate")
    public ApiResponse<String> validate(@Valid @RequestBody UploadValidateRequest req){
        validationService.validate(req.getFilename());
        return ApiResponse.ok("허용된 파일명입니다.");
    }

    /** 실제 업로드 검사(실제 저장 로직은 요구 범위 밖, 필요 시 추가) */
    @PostMapping("/upload")
    public ApiResponse<String> upload(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty() || file.getOriginalFilename() == null) {
            throw new IllegalArgumentException("업로드할 파일이 없습니다.");
        }
        validationService.validate(file.getOriginalFilename());
        return ApiResponse.ok("업로드 허용");
    }
}
