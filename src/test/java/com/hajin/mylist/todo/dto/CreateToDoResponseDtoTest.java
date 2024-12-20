package com.hajin.mylist.todo.dto;

import com.hajin.mylist.todo.entity.ToDo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateToDoResponseDtoTest {

    @Test
    @DisplayName("CreateToDoResponseDto 기본 생성자 테스트 - 성공 메시지 확인")
    void testCreateToDoResponseDtoDefaultConstructor() {
        // Given: 아무 것도 없음

        // When
        CreateToDoResponseDto responseDto = new CreateToDoResponseDto();

        // Then
        assertEquals("작성이 완료되었습니다.", responseDto.getMessege(),
                "The message should be '작성이 완료되었습니다.'");
    }
}
