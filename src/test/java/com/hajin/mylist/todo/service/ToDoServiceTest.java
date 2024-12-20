package com.hajin.mylist.todo.service;

import com.hajin.mylist.exception.CustomException;
import com.hajin.mylist.exception.ErrorMsg;
import com.hajin.mylist.todo.dto.*;
import com.hajin.mylist.todo.entity.ToDo;
import com.hajin.mylist.todo.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
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
    @DisplayName("할 일 생성 - 성공 케이스")
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
    @DisplayName("할 일 수정 - 성공 케이스")
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
    @DisplayName("할 일 수정 - ToDo 항목을 찾을 수 없을 때 예외 발생")
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
    @DisplayName("할 일 단건 조회 - 성공 케이스")
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
    @DisplayName("할 일 단건 조회 - ToDo 항목을 찾을 수 없을 때 예외 발생")
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
    @DisplayName("할 일 삭제 - 성공 케이스")
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
    @DisplayName("할 일 삭제 - ToDo 항목을 찾을 수 없을 때 예외 발생")
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
    @DisplayName("특정 날짜의 ToDo 목록 조회 - 성공 케이스")
    void getToDosByDate_Success() {
        // Given
        LocalDate date = LocalDate.of(2024, 12, 25);
        GetToDoByDateRequestDto requestDto = mock(GetToDoByDateRequestDto.class);
        when(requestDto.getDate()).thenReturn(date);

        ToDo mockToDo = mock(ToDo.class);
        when(mockToDo.getId()).thenReturn(1L);
        when(mockToDo.getTitle()).thenReturn("Test Title");
        when(mockToDo.getDescription()).thenReturn("Test Description");
        when(mockToDo.getDueDate()).thenReturn(date);

        when(toDoRepository.findAllByDueDate(date)).thenReturn(List.of(mockToDo));

        // When
        List<GetToDoByDateResponseDto> response = toDoService.getToDosByDate(requestDto);

        // Then
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Test Title", response.get(0).getTitle());
        assertEquals("Test Description", response.get(0).getDescription());
        assertEquals(date, response.get(0).getDueDate());
        verify(toDoRepository, times(1)).findAllByDueDate(date);
    }

    @Test
    @DisplayName("특정 날짜의 ToDo 목록 조회 - 예외 케이스 (목록 없음)")
    void getToDosByDate_NotFound() {
        // Given
        LocalDate date = LocalDate.of(2024, 12, 25);
        GetToDoByDateRequestDto requestDto = mock(GetToDoByDateRequestDto.class);
        when(requestDto.getDate()).thenReturn(date);

        when(toDoRepository.findAllByDueDate(date)).thenReturn(List.of());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () ->
                toDoService.getToDosByDate(requestDto));

        assertEquals(1001, exception.getCode());
        verify(toDoRepository, times(1)).findAllByDueDate(date);
    }


    @Test
    @DisplayName("전체 할 일 조회 - 성공 케이스")
    void getAllToDos_Success() {
        // Given
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);

        ToDo mockToDo = mock(ToDo.class);
        when(mockToDo.getTitle()).thenReturn("Test Title");
        when(mockToDo.getDueDate()).thenReturn(LocalDate.now());
        when(mockToDo.isCompleted()).thenReturn(false);

        List<ToDo> toDoList = List.of(mockToDo);
        Page<ToDo> toDoPage = new PageImpl<>(toDoList, pageable, toDoList.size());

        when(toDoRepository.findAll(pageable)).thenReturn(toDoPage);

        // When
        Page<GetAllToDoResponseDto> response = toDoService.getAllToDos(pageable);

        // Then
        assertEquals(1, response.getTotalElements());
        assertEquals("Test Title", response.getContent().get(0).getTitle());
        verify(toDoRepository, times(1)).findAll(pageable);
    }



    @Test
    @DisplayName("할 일 목록 조회 - 목록이 비어 있을 때 예외 발생")
    void getAllToDos_EmptyList() {
        // Given
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);

        when(toDoRepository.findAll(pageable)).thenReturn(Page.empty());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () -> toDoService.getAllToDos(pageable));

        assertEquals(ErrorMsg.TODO_NOT_FOUND.getCode(), exception.getCode());
        verify(toDoRepository, times(1)).findAll(pageable);
    }


    @Test
    @DisplayName("할 일 완료 여부 수정 - 성공 케이스")
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
    @DisplayName("할 일 완료 여부 수정 - ToDo 항목을 찾을 수 없을 때 예외 발생")
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
