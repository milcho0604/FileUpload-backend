package com.fileblocker.flow.file.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
public class UploadValidateRequest {
    @NotBlank(message="파일명을 입력하세요.")
    private String filename;
}
