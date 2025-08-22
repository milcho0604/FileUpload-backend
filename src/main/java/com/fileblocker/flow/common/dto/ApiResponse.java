package com.fileblocker.flow.common.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(T data){ return ApiResponse.<T>builder().success(true).message("OK").data(data).build(); }
    public static <T> ApiResponse<T> fail(String msg){ return ApiResponse.<T>builder().success(false).message(msg).data(null).build(); }
}
