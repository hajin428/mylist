package com.hajin.mylist.todo.dto;

import com.hajin.mylist.todo.entity.ToDo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetToDoResponseDtoTest {

    @Test
    @DisplayName("GetToDoResponseDto 기본 생성자 테스트")
    void testGetToDoResponseDtoDefaultConstructor() {
        // When
        GetToDoResponseDto responseDto = new GetToDoResponseDto();

        // Then
        assertNull(responseDto.getId(), "ID should be null by default.");
        assertNull(responseDto.getTitle(), "Title should be null by default.");
        assertNull(responseDto.getDescription(), "Description should be null by default.");
        assertNull(responseDto.getDueDate(), "DueDate should be null by default.");
        assertEquals(false, responseDto.isCompleted(), "Completed should be false by default.");
    }

    @Test
    @DisplayName("GetToDoResponseDto ToDo 객체 기반 생성자 테스트")
    void testGetToDoResponseDtoConstructor_WithMockToDo() {

        // Given
        ToDo mockToDo = mock(ToDo.class);
        Long mockId = 1L;
        String mockTitle = "Mock Title";
        String mockDescription = "Mock Description";
        LocalDate mockDueDate = LocalDate.of(2024, 12, 12);
        boolean mockCompleted = true;

        when(mockToDo.getId()).thenReturn(mockId);
        when(mockToDo.getTitle()).thenReturn(mockTitle);
        when(mockToDo.getDescription()).thenReturn(mockDescription);
        when(mockToDo.getDueDate()).thenReturn(mockDueDate);
        when(mockToDo.isCompleted()).thenReturn(mockCompleted);

        // When
        GetToDoResponseDto responseDto = new GetToDoResponseDto(mockToDo);

        // Then
        assertEquals(mockId, responseDto.getId());
        assertEquals(mockTitle, responseDto.getTitle());
        assertEquals(mockDescription, responseDto.getDescription());
        assertEquals(mockDueDate, responseDto.getDueDate());
        assertEquals(mockCompleted, responseDto.isCompleted());
    }
}
