package com.hajin.mylist.todo.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class CreateToDoRequestDtoTest {

    @Test
    @DisplayName("CreateToDoRequestDto 기본 생성자 테스트")
    void testDefaultConstructorAndGetters() {

        // Given
        CreateToDoRequestDto requestDto = new CreateToDoRequestDto();

        // Then
        assertNull(requestDto.getTitle());
        assertNull(requestDto.getDescription());
        assertNull(requestDto.getDueDate());
    }

}
