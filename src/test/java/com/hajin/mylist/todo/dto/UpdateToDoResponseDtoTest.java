package com.hajin.mylist.todo.dto;

import com.hajin.mylist.todo.entity.ToDo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class UpdateToDoResponseDtoTest {

    @Test
    @DisplayName("UpdateToDoResponseDto 생성자 테스트 - 메시지 확인")
    void testUpdateToDoResponseDtoConstructor() {

        // Given
        ToDo mockToDo = mock(ToDo.class);

        // When
        UpdateToDoResponseDto responseDto = new UpdateToDoResponseDto(mockToDo);

        // Then
        assertEquals("수정이 완료되었습니다.", responseDto.getMessage());
    }
}
