package com.pintoss.gitftmall.common.exceptions;

import com.pintoss.gitftmall.common.dto.ApiErrorResponse;
import com.pintoss.gitftmall.common.exceptions.client.BadRequestException;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value ={BadRequestException.class})
    public final ResponseEntity<ApiErrorResponse> handleBadRequestException(BadRequestException e){
        ApiErrorResponse errorResponse = ApiErrorResponse.of(
            e.getHttpStatus(),
            e.getErrorCode(),
            e.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(value = {BaseException.class})
    public final ResponseEntity<ApiErrorResponse> handleCustomException(BaseException ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.of(
            ex.getHttpStatus(),
            ex.getErrorCode(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
        log.error("ExceptionHandler catch HandlerMethodValidationException : {}", e.getMessage());
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ApiErrorResponse errorResponse = ApiErrorResponse.withErrors(
            HttpStatus.BAD_REQUEST,
            "유효하지 않은 요청입니다",
            errors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public final ResponseEntity<ApiErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
            errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );

        ApiErrorResponse errorResponse = ApiErrorResponse.withErrors(
            HttpStatus.BAD_REQUEST,
            ex.getMessage(),
            errors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
