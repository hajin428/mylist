package com.hajin.mylist.answer.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnswerGenerationResponseDtoTest {

    @Test
    @DisplayName("AnswerGenerationResponseDto 기본 생성자 테스트")
    void testDefaultConstructor() {
        // When
        AnswerGenerationResponseDto responseDto = new AnswerGenerationResponseDto();

        // Then
        assertEquals("응답이 성공적으로 저장되었습니다.", responseDto.getMessage());
    }

    @Test
    @DisplayName("AnswerGenerationResponseDto 모든 필드 초기화 테스트")
    void testAllArgsConstructor() {
        // Given
        String customMessage = "Custom success message";

        // When
        AnswerGenerationResponseDto responseDto = new AnswerGenerationResponseDto(customMessage);

        // Then
        assertEquals(customMessage, responseDto.getMessage());
    }
}
