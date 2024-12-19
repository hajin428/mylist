package com.hajin.mylist.answer.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnswerResponseDtoTest {

    @Test
    @DisplayName("AnswerResponseDto 생성자 테스트")
    void testAnswerResponseDtoConstructor() {

        // Given
        String testTitle = "테스트 제목";
        List<String> testContent = List.of("AI 응답 1", "AI 응답 2", "AI 응답 3");

        // When
        AnswerResponseDto responseDto = new AnswerResponseDto(testTitle, testContent);

        // Then
        assertEquals(testTitle, responseDto.getTitle());
        assertEquals(testContent, responseDto.getContent());
    }
}
