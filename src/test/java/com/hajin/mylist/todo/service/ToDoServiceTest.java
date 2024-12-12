package com.hajin.mylist.todo.service;

import com.hajin.mylist.exception.CustomException;
import com.hajin.mylist.exception.ErrorMsg;
import com.hajin.mylist.todo.dto.*;
import com.hajin.mylist.todo.entity.ToDo;
import com.hajin.mylist.todo.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    @InjectMocks
    private ToDoService toDoService;

    @Mock
    private ToDoRepository toDoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createToDo_Success() {

        // Given
        CreateToDoRequestDto requestDto = mock(CreateToDoRequestDto.class);
        ToDo mockToDo = mock(ToDo.class);
        when(toDoRepository.save(any(ToDo.class))).thenReturn(mockToDo);

        // When
        CreateToDoResponseDto response = toDoService.createToDo(requestDto);

        // Then
        assertNotNull(response);
        verify(toDoRepository, times(1)).save(any(ToDo.class));
    }


    @Test
    void updateToDo_Success() {

        // Given
        Long id = 1L;
        UpdateToDoRequestDto requestDto = mock(UpdateToDoRequestDto.class);
        ToDo mockToDo = mock(ToDo.class);
        when(toDoRepository.findById(id)).thenReturn(Optional.of(mockToDo));
        when(toDoRepository.save(any(ToDo.class))).thenReturn(mockToDo);

        // When
        UpdateToDoResponseDto response = toDoService.updateToDo(id, requestDto);

        // Then
        assertNotNull(response);
        verify(toDoRepository, times(1)).findById(id);
        verify(toDoRepository, times(1)).save(mockToDo);
    }


    @Test
    void updateToDo_ThrowsException_WhenNotFound() {

        // Given
        Long id = 1L;
        UpdateToDoRequestDto requestDto = mock(UpdateToDoRequestDto.class);
        when(toDoRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () -> {
            toDoService.updateToDo(id, requestDto);
        });
        assertEquals(ErrorMsg.TODO_NOT_FOUND.getCode(), exception.getCode());
    }


    @Test
    void getToDoById_Success() {

        // Given
        Long id = 1L;
        ToDo mockToDo = mock(ToDo.class);
        when(toDoRepository.findById(id)).thenReturn(Optional.of(mockToDo));

        // When
        GetToDoResponseDto response = toDoService.getToDoById(id);

        // Then
        assertNotNull(response);
        verify(toDoRepository, times(1)).findById(id);
    }


    @Test
    void getToDoById_ThrowsException_WhenNotFound() {
        // Given
        Long id = 1L;
        when(toDoRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () -> {
            toDoService.getToDoById(id);
        });
        assertEquals(ErrorMsg.TODO_NOT_FOUND.getCode(), exception.getCode());
    }


    @Test
    void deleteToDo_Success() {
        // Given
        Long id = 1L;
        ToDo mockToDo = mock(ToDo.class);
        when(toDoRepository.findById(id)).thenReturn(Optional.of(mockToDo));

        // When
        DeleteToDoResponseDto response = toDoService.deleteToDo(id);

        // Then
        assertNotNull(response);
        verify(toDoRepository, times(1)).delete(mockToDo);
    }


    @Test
    void deleteToDo_ThrowsException_WhenNotFound() {
        // Given
        Long id = 1L;
        when(toDoRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () -> {
            toDoService.deleteToDo(id);
        });
        assertEquals(ErrorMsg.TODO_NOT_FOUND.getCode(), exception.getCode());
    }


    @Test
    void getAllToDos_Success() {
        // Given
        List<ToDo> toDoList = new ArrayList<>();
        toDoList.add(mock(ToDo.class));
        when(toDoRepository.findAll()).thenReturn(toDoList);

        // When
        List<GetAllToDoResponseDto> response = toDoService.getAllToDos();

        // Then
        assertEquals(1, response.size());
        verify(toDoRepository, times(1)).findAll();
    }


    @Test
    void updateToDoCompleted_Success() {
        // Given
        Long id = 1L;
        CompletedUpdateRequestDto requestDto = mock(CompletedUpdateRequestDto.class);
        ToDo mockToDo = mock(ToDo.class);
        when(toDoRepository.findById(id)).thenReturn(Optional.of(mockToDo));
        when(toDoRepository.save(any(ToDo.class))).thenReturn(mockToDo);

        // When
        CompletedUpdateResponseDto response = toDoService.updateToDoCompleted(id, requestDto);

        // Then
        assertNotNull(response);
        verify(toDoRepository, times(1)).findById(id);
        verify(toDoRepository, times(1)).save(mockToDo);
    }


    @Test
    void updateToDoCompleted_ThrowsException_WhenNotFound() {
        // Given
        Long id = 1L;
        CompletedUpdateRequestDto requestDto = mock(CompletedUpdateRequestDto.class);
        when(toDoRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () -> {
            toDoService.updateToDoCompleted(id, requestDto);
        });
        assertEquals(ErrorMsg.TODO_NOT_FOUND.getCode(), exception.getCode());
    }
}
