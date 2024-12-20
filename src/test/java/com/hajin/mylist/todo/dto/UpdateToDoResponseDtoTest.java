package com.hajin.mylist.todo.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateToDoResponseDtoTest {

    @Test
    @DisplayName("UpdateToDoResponseDto 기본 생성자 테스트 - 메시지 확인")
    void testUpdateToDoResponseDtoDefaultConstructor() {

        // When
        UpdateToDoResponseDto responseDto = new UpdateToDoResponseDto();

        // Then
        assertEquals("수정이 완료되었습니다.", responseDto.getMessage(),
                "The message should be '수정이 완료되었습니다.'");
    }
}
