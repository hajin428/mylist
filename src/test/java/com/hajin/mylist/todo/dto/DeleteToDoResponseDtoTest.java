package com.hajin.mylist.todo.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteToDoResponseDtoTest {

    @Test
    @DisplayName("DeleteToDoResponseDto 생성자 테스트 - 성공 메시지 확인")
    void testDeleteToDoResponseDtoConstructor_Success() {

        // When
        DeleteToDoResponseDto responseDto = new DeleteToDoResponseDto();

        // Then
        assertEquals("항목이 삭제되었습니다.", responseDto.getMessage());
    }
}
