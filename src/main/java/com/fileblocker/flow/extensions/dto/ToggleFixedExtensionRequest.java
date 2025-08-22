package com.fileblocker.flow.extensions.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
public class ToggleFixedExtensionRequest {
    @NotBlank(message="확장자를 입력하세요.")
    private String name;     // .포함 입력 허용, 서버 표준화
    private boolean blocked;
}
