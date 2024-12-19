package com.hajin.mylist.todo.entity;

import com.hajin.mylist.todo.dto.CompletedUpdateRequestDto;
import com.hajin.mylist.todo.dto.CreateToDoRequestDto;
import com.hajin.mylist.todo.dto.UpdateToDoRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ToDoTest {

    @Test
    @DisplayName("CreateToDoRequestDto를 사용한 생성자 테스트")
    void testConstructorWithCreateToDoRequestDto() {
        // Given
        CreateToDoRequestDto requestDto = mock(CreateToDoRequestDto.class);
        when(requestDto.getTitle()).thenReturn("Test Title");
        when(requestDto.getDescription()).thenReturn("Test Description");
        when(requestDto.getDueDate()).thenReturn(LocalDate.of(2024, 12, 12));

        // When
        ToDo toDo = new ToDo(requestDto);

        // Then
        assertEquals("Test Title", toDo.getTitle());
        assertEquals("Test Description", toDo.getDescription());
        assertEquals(LocalDate.of(2024, 12, 12), toDo.getDueDate());
        assertFalse(toDo.isCompleted());
    }

    @Test
    @DisplayName("UpdateToDoRequestDto를 사용한 업데이트 테스트")
    void testUpdateWithUpdateToDoRequestDto() {
        // Given
        ToDo toDo = new ToDo();
        UpdateToDoRequestDto requestDto = mock(UpdateToDoRequestDto.class);
        when(requestDto.getTitle()).thenReturn("Updated Title");
        when(requestDto.getDescription()).thenReturn("Updated Description");
        when(requestDto.getDueDate()).thenReturn(LocalDate.of(2024, 12, 25));

        // When
        toDo.update(requestDto);

        // Then
        assertEquals("Updated Title", toDo.getTitle());
        assertEquals("Updated Description", toDo.getDescription());
        assertEquals(LocalDate.of(2024, 12, 25), toDo.getDueDate());
        assertFalse(toDo.isCompleted());
    }

    @Test
    @DisplayName("CompletedUpdateRequestDto를 사용한 완료 여부 수정 테스트")
    void testSetCompletedWithCompletedUpdateRequestDto() {
        // Given
        ToDo toDo = new ToDo();
        CompletedUpdateRequestDto requestDto = mock(CompletedUpdateRequestDto.class);
        when(requestDto.isCompleted()).thenReturn(true);

        // When
        toDo.setCompleted(requestDto);

        // Then
        assertEquals(true, toDo.isCompleted());
    }

    @Test
    @DisplayName("AllArgsConstructor를 사용한 생성자 테스트")
    void testAllArgsConstructor() {
        // Given
        Long id = 1L;
        String title = "AllArgsConstructor Title";
        String description = "AllArgsConstructor Description";
        LocalDate dueDate = LocalDate.of(2024, 12, 31);
        boolean completed = false;

        // When
        ToDo toDo = new ToDo(id, title, description, dueDate, completed, new ArrayList<>());

        // Then
        assertEquals(id, toDo.getId());
        assertEquals(title, toDo.getTitle());
        assertEquals(description, toDo.getDescription());
        assertEquals(dueDate, toDo.getDueDate());
        assertEquals(completed, toDo.isCompleted());
        assertEquals(0, toDo.getAnswers().size());
    }
}
