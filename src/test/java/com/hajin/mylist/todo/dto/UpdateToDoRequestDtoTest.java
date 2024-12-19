package com.hajin.mylist.todo.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class UpdateToDoRequestDtoTest {

    @Test
    @DisplayName("UpdateToDoRequestDto 기본 생성자 및 Getter 메서드 검증")
    void testDefaultConstructorAndGetters() {

        // When
        UpdateToDoRequestDto requestDto = new UpdateToDoRequestDto();

        // Then
        assertNull(requestDto.getTitle());
        assertNull(requestDto.getDescription());
        assertNull(requestDto.getDueDate());
        assertFalse(requestDto.isCompleted());
    }
}
