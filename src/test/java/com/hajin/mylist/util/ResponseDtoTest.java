package com.hajin.mylist.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseDtoTest {

    @Test
    @DisplayName("toExceptionResponseEntity 메서드 테스트")
    void testToExceptionResponseEntity_BadRequest() {
        // Given
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        int errorCode = 1001;

        // When
        ResponseEntity<ResponseDto> responseEntity = ResponseDto.toExceptionResponseEntity(httpStatus, errorCode);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
        assertEquals(errorCode, responseEntity.getBody().getCode());
        assertEquals("An error occurred. Please contact developer.", responseEntity.getBody().getMessage());
    }

    @Test
    @DisplayName("toExceptionResponseEntity 메서드 테스트 - INTERNAL_SERVER_ERROR")
    void testToExceptionResponseEntity_InternalServerError() {
        // Given
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        int errorCode = 5000;

        // When
        ResponseEntity<ResponseDto> responseEntity = ResponseDto.toExceptionResponseEntity(httpStatus, errorCode);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
        assertEquals(errorCode, responseEntity.getBody().getCode());
        assertEquals("An error occurred. Please contact developer.", responseEntity.getBody().getMessage());
    }
}
