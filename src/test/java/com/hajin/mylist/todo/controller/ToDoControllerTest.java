package com.hajin.mylist.todo.controller;

import com.hajin.mylist.todo.dto.*;
import com.hajin.mylist.todo.service.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ToDoControllerTest {

    private ToDoController toDoController;

    @Mock
    private ToDoService toDoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        toDoController = new ToDoController(toDoService);
    }

    @Test
    @DisplayName("할 일 생성 테스트")
    void createToDo() {
        // Given
        CreateToDoRequestDto requestDto = mock(CreateToDoRequestDto.class);
        CreateToDoResponseDto responseDto = mock(CreateToDoResponseDto.class);
        when(toDoService.createToDo(any(CreateToDoRequestDto.class))).thenReturn(responseDto);

        // When
        ResponseEntity<CreateToDoResponseDto> response = toDoController.createToDo(requestDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    @DisplayName("할 일 수정 테스트")
    void updateToDo() {
        // Given
        Long id = 1L;
        UpdateToDoRequestDto requestDto = mock(UpdateToDoRequestDto.class);
        UpdateToDoResponseDto responseDto = mock(UpdateToDoResponseDto.class);
        when(toDoService.updateToDo(any(Long.class), any(UpdateToDoRequestDto.class))).thenReturn(responseDto);

        // When
        ResponseEntity<UpdateToDoResponseDto> response = toDoController.updateToDo(id, requestDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    @DisplayName("할 일 단건 조회 테스트")
    void getToDoById() {
        // Given
        Long id = 1L;
        GetToDoResponseDto responseDto = mock(GetToDoResponseDto.class);
        when(toDoService.getToDoById(any(Long.class))).thenReturn(responseDto);

        // When
        ResponseEntity<GetToDoResponseDto> response = toDoController.getToDoById(id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    @DisplayName("할 일 삭제 테스트")
    void deleteToDo() {
        // Given
        Long id = 1L;
        DeleteToDoResponseDto responseDto = mock(DeleteToDoResponseDto.class);
        when(toDoService.deleteToDo(any(Long.class))).thenReturn(responseDto);

        // When
        ResponseEntity<DeleteToDoResponseDto> response = toDoController.deleteToDo(id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    @DisplayName("전체 할 일 조회 테스트")
    void getAllToDos() {
        // Given
        int page = 0;
        int size = 20;
        Pageable pageable = PageRequest.of(page, size);

        Page<GetAllToDoResponseDto> mockPage = mock(Page.class);
        when(toDoService.getAllToDos(pageable)).thenReturn(mockPage);

        // When
        ResponseEntity<Page<GetAllToDoResponseDto>> response = toDoController.getAllToDos(page, size);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPage, response.getBody());
    }


    @Test
    @DisplayName("할 일 완료 여부 수정 테스트")
    void updateToDoCompleted() {
        // Given
        Long id = 1L;
        CompletedUpdateRequestDto requestDto = mock(CompletedUpdateRequestDto.class);
        CompletedUpdateResponseDto responseDto = mock(CompletedUpdateResponseDto.class);
        when(toDoService.updateToDoCompleted(any(Long.class), any(CompletedUpdateRequestDto.class))).thenReturn(responseDto);

        // When
        ResponseEntity<CompletedUpdateResponseDto> response = toDoController.updateToDoCompleted(id, requestDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    @DisplayName("특정 날짜의 할 일 조회 테스트")
    void getToDosByDate() {
        // Given
        GetToDoByDateRequestDto requestDto = mock(GetToDoByDateRequestDto.class);
        List<GetToDoByDateResponseDto> responseDtos = mock(List.class);
        when(toDoService.getToDosByDate(any(GetToDoByDateRequestDto.class))).thenReturn(responseDtos);

        // When
        ResponseEntity<List<GetToDoByDateResponseDto>> response = toDoController.getToDosByDate(requestDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDtos, response.getBody());
    }
}
