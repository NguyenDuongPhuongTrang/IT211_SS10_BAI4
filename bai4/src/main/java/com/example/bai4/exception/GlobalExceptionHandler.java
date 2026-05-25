package com.example.bai4.exception;

import com.example.bai4.model.dto.response.ApiDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiDataResponse<String>> handleBusinessException(BusinessException exception) {
        log.warn(exception.getMessage());
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                exception.getMessage(),
                null,
                null,
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiDataResponse<String>> handleApiDataResponse(NullPointerException exception) {
        log.error("Lỗi máy chủ nội bộ.");
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Lỗi máy chủ nội bộ. Vui lòng liên hệ quản trị viên",
                null,
                null,
                HttpStatus.INTERNAL_SERVER_ERROR
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDataResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn("Dữ liệu không hợp lệ");
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Dữ liệu không hợp lệ",
                null,
                errors,
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }
}
