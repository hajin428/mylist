package com.hajin.mylist.todo.dto;

import com.hajin.mylist.todo.entity.ToDo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompletedUpdateResponseDtoTest {

    @Test
    @DisplayName("CompletedUpdateResponseDto 생성자 테스트")
    void testCompletedUpdateResponseDtoConstructor_Success_WithMockToDo() {

        // Given
        ToDo mockToDo = Mockito.mock(ToDo.class);

        // When
        CompletedUpdateResponseDto responseDto = new CompletedUpdateResponseDto(mockToDo);

        // Then
        assertEquals("수정이 완료되었습니다.", responseDto.getMessege(),
                "The message should be '수정이 완료되었습니다.'");
    }
}
