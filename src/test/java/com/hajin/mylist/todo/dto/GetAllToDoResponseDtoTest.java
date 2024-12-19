package com.hajin.mylist.todo.dto;

import com.hajin.mylist.todo.entity.ToDo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetAllToDoResponseDtoTest {

    @Test
    @DisplayName("GetAllToDoResponseDto 생성자 테스트")
    void testGetAllToDoResponseDtoConstructor_WithMockToDo() {

        // Given
        ToDo mockToDo = mock(ToDo.class);
        Long mockId = 1L;
        String mockTitle = "Title";
        String mockDescription = "Description";
        LocalDate mockDueDate = LocalDate.of(2024, 12, 12);
        boolean mockCompleted = true;

        when(mockToDo.getId()).thenReturn(mockId);
        when(mockToDo.getTitle()).thenReturn(mockTitle);
        when(mockToDo.getDescription()).thenReturn(mockDescription);
        when(mockToDo.getDueDate()).thenReturn(mockDueDate);
        when(mockToDo.isCompleted()).thenReturn(mockCompleted);

        // When
        GetAllToDoResponseDto responseDto = new GetAllToDoResponseDto(mockToDo);

        // Then
        assertEquals(mockId, responseDto.getId());
        assertEquals(mockTitle, responseDto.getTitle());
        assertEquals(mockDescription, responseDto.getDescription());
        assertEquals(mockDueDate, responseDto.getDueDate());
        assertEquals(mockCompleted, responseDto.isCompleted());
    }
}
