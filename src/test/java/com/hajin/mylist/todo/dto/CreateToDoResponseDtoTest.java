package com.hajin.mylist.todo.dto;

import com.hajin.mylist.todo.entity.ToDo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateToDoResponseDtoTest {

    @Test
    @DisplayName("CreateToDoResponseDto 생성자 테스트 - 성공 메시지 확인")
    void testCreateToDoResponseDtoConstructor_WithMockToDo() {

        // Given
        ToDo mockToDo = Mockito.mock(ToDo.class);

        // When
        CreateToDoResponseDto responseDto = new CreateToDoResponseDto(mockToDo);

        // Then
        assertEquals("작성이 완료되었습니다.", responseDto.getMessege());
    }
}
