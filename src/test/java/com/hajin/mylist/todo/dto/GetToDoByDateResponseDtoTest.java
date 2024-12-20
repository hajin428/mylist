package com.hajin.mylist.todo.dto;

import com.hajin.mylist.todo.entity.ToDo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetToDoByDateResponseDtoTest {

    @Test
    @DisplayName("GetToDoByDateResponseDto 기본 생성자 테스트")
    void testGetToDoByDateResponseDtoDefaultConstructor() {
        // When
        GetToDoByDateResponseDto responseDto = new GetToDoByDateResponseDto();

        // Then
        assertNull(responseDto.getTitle(), "Title should be null by default.");
        assertNull(responseDto.getDescription(), "Description should be null by default.");
        assertNull(responseDto.getDueDate(), "DueDate should be null by default.");
        assertEquals(false, responseDto.isCompleted(), "Completed should be false by default.");
    }

    @Test
    @DisplayName("GetToDoByDateResponseDto ToDo 객체 기반 생성자 테스트")
    void testGetToDoByDateResponseDtoConstructor_WithMockToDo() {

        // Given
        ToDo mockToDo = mock(ToDo.class);
        String mockTitle = "Mock Title";
        String mockDescription = "Mock Description";
        LocalDate mockDueDate = LocalDate.of(2024, 12, 12);
        boolean mockCompleted = true;

        when(mockToDo.getTitle()).thenReturn(mockTitle);
        when(mockToDo.getDescription()).thenReturn(mockDescription);
        when(mockToDo.getDueDate()).thenReturn(mockDueDate);
        when(mockToDo.isCompleted()).thenReturn(mockCompleted);

        // When
        GetToDoByDateResponseDto responseDto = new GetToDoByDateResponseDto(mockToDo);

        // Then
        assertEquals(mockTitle, responseDto.getTitle());
        assertEquals(mockDescription, responseDto.getDescription());
        assertEquals(mockDueDate, responseDto.getDueDate());
        assertEquals(mockCompleted, responseDto.isCompleted());
    }
}
