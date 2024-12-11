package com.hajin.mylist.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class ResponseDto {

    private int statusCode;
    private int code;
    private String message;

    public static ResponseEntity<ResponseDto> toExceptionResponseEntity(HttpStatus httpStatus, int code) {
        return ResponseEntity
                .status(httpStatus)
                .body(ResponseDto.builder()
                        .code(code)
                        .message("An error occurred. Please contact developer.")
                        .build());
    }
}
