package com.hajin.mylist.exception;

import com.hajin.mylist.util.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // CustomException
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ResponseDto> handleCustomException(CustomException exception) {
        log.error("CustomException occurred: {}", exception.getMessage());
        return ResponseDto.toExceptionResponseEntity(
                exception.getHttpStatus(),
                exception.getCode());
    }

    // 기타 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleAllExceptions(Exception exception) {
        log.error("Unexpected error occurred: {}", exception);
        return ResponseDto.toExceptionResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
                9999);
    }
}
