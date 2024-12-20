package com.hajin.mylist.answer.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AnswerGenerationRequestDtoTest {

    @Test
    @DisplayName("AnswerGenerationRequestDto 기본 생성자 테스트")
    void testDefaultConstructor() {
        // When
        AnswerGenerationRequestDto requestDto = new AnswerGenerationRequestDto();

        // Then
        assertNull(requestDto.getDate());
    }

    @Test
    @DisplayName("AnswerGenerationRequestDto 모든 필드 초기화 테스트")
    void testAllArgsConstructor() {
        // Given
        LocalDate testDate = LocalDate.of(2024, 12, 20);

        // When
        AnswerGenerationRequestDto requestDto = new AnswerGenerationRequestDto(testDate);

        // Then
        assertEquals(testDate, requestDto.getDate());
    }
}
