package com.hajin.mylist.todo.entity;

import com.hajin.mylist.todo.dto.CompletedUpdateRequestDto;
import com.hajin.mylist.todo.dto.CreateToDoRequestDto;
import com.hajin.mylist.todo.dto.UpdateToDoRequestDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ToDoTest {

    @Test
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
}
