package com.hajin.mylist.prompt.mapper;

import com.hajin.mylist.todo.entity.ToDo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ToDoMapperTest {

    @Test
    @DisplayName("ToDoMapper - convertToMap 메서드 테스트")
    void convertToMapTest() {
        // Given
        ToDo mockToDo = mock(ToDo.class);
        when(mockToDo.getId()).thenReturn(1L);
        when(mockToDo.getTitle()).thenReturn("Test Title");
        when(mockToDo.getDescription()).thenReturn("Test Description");

        // When
        Map<String, Object> result = ToDoMapper.convertToMap(mockToDo);

        // Then
        assertEquals(1L, result.get("id"));
        assertEquals("Test Title", result.get("제목"));
        assertEquals("Test Description", result.get("설명"));

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> preparations = (List<Map<String, Object>>) result.get("preparations");
        assertEquals(3, preparations.size());

        // Verify first preparation map
        Map<String, Object> firstPreparation = preparations.get(0);
        assertEquals(1, firstPreparation.get("task_key"));
        assertEquals("", firstPreparation.get("value"));

        // Verify second preparation map
        Map<String, Object> secondPreparation = preparations.get(1);
        assertEquals(2, secondPreparation.get("task_key"));
        assertEquals("", secondPreparation.get("value"));

        // Verify third preparation map
        Map<String, Object> thirdPreparation = preparations.get(2);
        assertEquals(3, thirdPreparation.get("task_key"));
        assertEquals("", thirdPreparation.get("value"));
    }
}
