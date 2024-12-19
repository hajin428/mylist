package com.hajin.mylist.answer.controller;

import com.hajin.mylist.answer.dto.AnswerResponseDto;
import com.hajin.mylist.answer.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AnswerControllerTest {

    private AnswerController answerController;

    @Mock
    private AnswerService answerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        answerController = new AnswerController(answerService);
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
}
