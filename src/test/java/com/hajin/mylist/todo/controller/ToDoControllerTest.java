package com.hajin.mylist.todo.controller;

import com.hajin.mylist.todo.dto.*;
import com.hajin.mylist.todo.service.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
    void updateToDo() {
        // Given
        Long id = 1L;
        UpdateToDoRequestDto requestDto = mock(UpdateToDoRequestDto.class);
        UpdateToDoResponseDto responseDto = mock(UpdateToDoResponseDto.class);
        when(toDoService.updateToDo(anyLong(), any(UpdateToDoRequestDto.class))).thenReturn(responseDto);

        // When
        ResponseEntity<UpdateToDoResponseDto> response = toDoController.updateToDo(id, requestDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void getToDoById() {
        // Given
        Long id = 1L;
        GetToDoResponseDto responseDto = mock(GetToDoResponseDto.class);
        when(toDoService.getToDoById(anyLong())).thenReturn(responseDto);

        // When
        ResponseEntity<GetToDoResponseDto> response = toDoController.getToDoById(id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void deleteToDo() {
        // Given
        Long id = 1L;
        DeleteToDoResponseDto responseDto = mock(DeleteToDoResponseDto.class);
        when(toDoService.deleteToDo(anyLong())).thenReturn(responseDto);

        // When
        ResponseEntity<DeleteToDoResponseDto> response = toDoController.deleteToDo(id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void getAllToDos() {
        // Given
        List<GetAllToDoResponseDto> responseDtos = mock(List.class);
        when(toDoService.getAllToDos()).thenReturn(responseDtos);

        // When
        ResponseEntity<List<GetAllToDoResponseDto>> response = toDoController.getAllToDos();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDtos, response.getBody());
    }

    @Test
    void updateToDoCompleted() {
        // Given
        Long id = 1L;
        CompletedUpdateRequestDto requestDto = mock(CompletedUpdateRequestDto.class);
        CompletedUpdateResponseDto responseDto = mock(CompletedUpdateResponseDto.class);
        when(toDoService.updateToDoCompleted(anyLong(), any(CompletedUpdateRequestDto.class))).thenReturn(responseDto);

        // When
        ResponseEntity<CompletedUpdateResponseDto> response = toDoController.updateToDoCompleted(id, requestDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }
}