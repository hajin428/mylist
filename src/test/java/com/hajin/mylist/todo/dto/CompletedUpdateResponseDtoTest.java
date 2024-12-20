package com.hajin.mylist.todo.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompletedUpdateResponseDtoTest {

    @Test
    @DisplayName("CompletedUpdateResponseDto 기본 생성자 테스트")
    void testCompletedUpdateResponseDtoDefaultConstructor() {

        // When
        CompletedUpdateResponseDto responseDto = new CompletedUpdateResponseDto();

        // Then
        assertEquals("수정이 완료되었습니다.", responseDto.getMessege(),
                "The message should be '수정이 완료되었습니다.'");
    }
}
