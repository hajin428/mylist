package com.hajin.mylist.todo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CompletedUpdateRequestDtoTest {

    @Test
    void testCompletedUpdateRequestDto_DefaultConstructor() {

        // When
        CompletedUpdateRequestDto requestDto = new CompletedUpdateRequestDto();

        // Then
        assertFalse(requestDto.isCompleted(), "Default value of 'completed' should be false");
    }
}
