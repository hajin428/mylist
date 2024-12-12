package com.hajin.mylist.todo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class UpdateToDoRequestDtoTest {

    @Test
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
