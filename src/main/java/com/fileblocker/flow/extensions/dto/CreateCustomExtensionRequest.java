package com.fileblocker.flow.extensions.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
public class CreateCustomExtensionRequest {
    @NotBlank(message="확장자를 입력하세요.")
    @Size(max=20, message="확장자는 최대 20자입니다.")
    private String name;     // .포함 입력 허용, 서버 표준화
}