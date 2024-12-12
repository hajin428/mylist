package com.hajin.mylist.todo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class CreateToDoRequestDtoTest {

    @Test
    void testDefaultConstructorAndGetters() {

        // Given
        CreateToDoRequestDto requestDto = new CreateToDoRequestDto();

        // Then
        assertNull(requestDto.getTitle());
        assertNull(requestDto.getDescription());
        assertNull(requestDto.getDueDate());
    }

}
