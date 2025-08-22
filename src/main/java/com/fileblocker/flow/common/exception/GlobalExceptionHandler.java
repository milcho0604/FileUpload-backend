package com.fileblocker.flow.common.exception;

import com.fileblocker.flow.common.dto.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleValidation(MethodArgumentNotValidException e){
        var msg = e.getBindingResult().getFieldErrors().stream()
                .findFirst().map(err -> err.getDefaultMessage()).orElse("유효성 오류");
        return ApiResponse.fail(msg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleConstraint(ConstraintViolationException e){
        return ApiResponse.fail(e.getMessage());
    }

    @ExceptionHandler(DuplicateExtensionException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<?> handleDup(DuplicateExtensionException e){ return ApiResponse.fail(e.getMessage()); }

    @ExceptionHandler(LimitExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleLimit(LimitExceededException e){ return ApiResponse.fail(e.getMessage()); }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleBiz(BusinessException e){ return ApiResponse.fail(e.getMessage()); }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handleEtc(Exception e){ return ApiResponse.fail("서버 오류"); }
}
