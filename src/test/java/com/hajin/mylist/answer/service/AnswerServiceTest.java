package com.hajin.mylist.answer.service;

import com.hajin.mylist.answer.dto.AnswerResponseDto;
import com.hajin.mylist.answer.entity.Answer;
import com.hajin.mylist.answer.repository.AnswerRepository;
import com.hajin.mylist.exception.CustomException;
import com.hajin.mylist.exception.ErrorMsg;
import com.hajin.mylist.todo.entity.ToDo;
import com.hajin.mylist.todo.repository.ToDoRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private AnswerService answerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("정상적인 JSON 응답 저장 테스트")
    void saveAnswersFromGptResponse_Success() {
        // Given
        JSONObject jsonResponse = new JSONObject("""
                {
                    "tasks": [
                        {
                            "id": 1,
                            "preparations": [
                                {"task_key": 1, "value": "Step 1"},
                                {"task_key": 2, "value": "Step 2"}
                            ]
                        }
                    ]
                }
                """);
        ToDo mockToDo = mock(ToDo.class);
        when(toDoRepository.findById(1L)).thenReturn(java.util.Optional.of(mockToDo));

        // When
        answerService.saveAnswersFromGptResponse(jsonResponse);

        // Then
        verify(toDoRepository, times(1)).findById(1L);
        verify(answerRepository, times(2)).save(any(Answer.class));
    }

    @Test
    @DisplayName("JSON 응답 저장 실패 - ToDo를 찾을 수 없는 경우")
    void saveAnswersFromGptResponse_ToDoNotFound() {
        // Given
        JSONObject jsonResponse = new JSONObject("""
                {
                    "tasks": [
                        {
                            "id": 1,
                            "preparations": [
                                {"task_key": 1, "value": "Step 1"}
                            ]
                        }
                    ]
                }
                """);
        when(toDoRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () ->
                answerService.saveAnswersFromGptResponse(jsonResponse));

        assertEquals(1000, exception.getCode());
        verify(toDoRepository, times(1)).findById(1L);
        verify(answerRepository, never()).save(any(Answer.class));
    }

    @Test
    @DisplayName("정상적인 ToDo ID로 Answer 목록 조회 테스트")
    void getAnswersByToDoId_Success() {
        // Given
        Long toDoId = 1L;
        ToDo mockToDo = mock(ToDo.class);
        when(mockToDo.getTitle()).thenReturn("Test Title");

        Answer answer1 = new Answer(mockToDo, 1, "Content 1", LocalDateTime.now());
        Answer answer2 = new Answer(mockToDo, 2, "Content 2", LocalDateTime.now());

        when(toDoRepository.findById(toDoId)).thenReturn(java.util.Optional.of(mockToDo));
        when(answerRepository.findAllByToDoId(toDoId)).thenReturn(List.of(answer1, answer2));

        // When
        List<AnswerResponseDto> result = answerService.getAnswersByToDoId(toDoId);

        // Then
        assertEquals(1, result.size());
        assertEquals("Test Title", result.get(0).getTitle());
        assertEquals(List.of("Content 1", "Content 2"), result.get(0).getContent());
    }

    @Test
    @DisplayName("ToDo ID로 Answer 조회 실패 - ToDo를 찾을 수 없는 경우")
    void getAnswersByToDoId_ToDoNotFound() {
        // Given
        Long toDoId = 1L;
        when(toDoRepository.findById(toDoId)).thenReturn(java.util.Optional.empty());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () ->
                answerService.getAnswersByToDoId(toDoId));

        assertEquals(1000, exception.getCode());
        verify(toDoRepository, times(1)).findById(toDoId);
        verify(answerRepository, never()).findAllByToDoId(anyLong());
    }

    @Test
    @DisplayName("ToDo ID로 Answer 조회 실패 - Answer가 없는 경우")
    void getAnswersByToDoId_AnswersNotFound() {
        // Given
        Long toDoId = 1L;
        ToDo mockToDo = mock(ToDo.class);
        when(toDoRepository.findById(toDoId)).thenReturn(java.util.Optional.of(mockToDo));
        when(answerRepository.findAllByToDoId(toDoId)).thenReturn(Collections.emptyList());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () ->
                answerService.getAnswersByToDoId(toDoId));
        assertEquals(1002, exception.getCode());
        verify(toDoRepository, times(1)).findById(toDoId);
        verify(answerRepository, times(1)).findAllByToDoId(toDoId);
    }
}
