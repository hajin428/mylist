package com.hajin.mylist.todo.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CompletedUpdateRequestDtoTest {

    @Test
    @DisplayName("CompletedUpdateRequestDto 기본 생성자 테스트")
    void testCompletedUpdateRequestDto_DefaultConstructor() {

        // When
        CompletedUpdateRequestDto requestDto = new CompletedUpdateRequestDto();

        // Then
        assertFalse(requestDto.isCompleted(), "Default value of 'completed' should be false");
    }
}
