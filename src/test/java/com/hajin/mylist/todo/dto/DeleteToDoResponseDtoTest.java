package com.hajin.mylist.todo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteToDoResponseDtoTest {

    @Test
    void testDeleteToDoResponseDtoConstructor_Success() {

        // When
        DeleteToDoResponseDto responseDto = new DeleteToDoResponseDto();

        // Then
        assertEquals("항목이 삭제되었습니다.", responseDto.getMessage());
    }
}
