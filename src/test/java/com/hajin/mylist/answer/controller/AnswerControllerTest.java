package com.hajin.mylist.answer.controller;

import com.hajin.mylist.answer.dto.AnswerGenerationRequestDto;
import com.hajin.mylist.answer.dto.AnswerGenerationResponseDto;
import com.hajin.mylist.answer.dto.AnswerResponseDto;
import com.hajin.mylist.answer.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AnswerControllerTest {

    @InjectMocks
    private AnswerController answerController;

    @Mock
    private AnswerService answerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("특정 ToDo에 연관된 Answer 목록 조회 테스트")
    void getAnswersByToDoId() {

        // Given
        Long toDoId = 1L;
        List<AnswerResponseDto> mockResponseDtos = mock(List.class);
        when(answerService.getAnswersByToDoId(anyLong())).thenReturn(mockResponseDtos);

        // When
        ResponseEntity<List<AnswerResponseDto>> response = answerController.getAnswersByToDoId(toDoId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponseDtos, response.getBody());

        verify(answerService, times(1)).getAnswersByToDoId(toDoId);
    }


    @Test
    @DisplayName("프롬프트 생성 및 AI 응답 저장 테스트")
    void generateAndSaveAnswers() {
        // Given
        LocalDate testDate = LocalDate.of(2024, 12, 20);
        AnswerGenerationRequestDto requestDto = new AnswerGenerationRequestDto(testDate);
        AnswerGenerationResponseDto mockResponseDto = new AnswerGenerationResponseDto("AI 응답이 성공적으로 저장되었습니다.");

        when(answerService.generateAndSaveAnswers(any(LocalDate.class))).thenReturn(mockResponseDto);

        // When
        ResponseEntity<AnswerGenerationResponseDto> response = answerController.generateAndSaveAnswers(requestDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockResponseDto, response.getBody());

        verify(answerService, times(1)).generateAndSaveAnswers(testDate);
    }
}
