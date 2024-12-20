package com.hajin.mylist.prompt.formatter;

import com.hajin.mylist.prompt.mapper.ToDoMapper;
import com.hajin.mylist.todo.entity.ToDo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.json.JSONObject;

class ToDoFormatterTest {

    @Test
    @DisplayName("ToDoFormatter - print 메서드 테스트")
    void printTest() {
        // Given
        ToDo mockToDo = mock(ToDo.class);
        when(mockToDo.getId()).thenReturn(1L);
        when(mockToDo.getTitle()).thenReturn("Test Title");
        when(mockToDo.getDescription()).thenReturn("Test Description");
        when(mockToDo.getDueDate()).thenReturn(LocalDate.of(2024, 12, 25));
        when(mockToDo.isCompleted()).thenReturn(false);

        Map<String, Object> mockToDoMap = Map.of(
                "id", 1L,
                "title", "Test Title",
                "description", "Test Description",
                "dueDate", "2024-12-25",
                "completed", false
        );

        try (MockedStatic<ToDoMapper> mockedMapper = mockStatic(ToDoMapper.class)) {
            mockedMapper.when(() -> ToDoMapper.convertToMap(mockToDo)).thenReturn(mockToDoMap);

            ToDoFormatter formatter = new ToDoFormatter();

            // When
            String result = formatter.print(mockToDo, Locale.getDefault());

            // Then
            String expectedJson = """
            {
              "id": 1,
              "title": "Test Title",
              "description": "Test Description",
              "dueDate": "2024-12-25",
              "completed": false
            }
            """;

            // JSON 객체로 비교
            JSONObject expected = new JSONObject(expectedJson);
            JSONObject actual = new JSONObject(result);

            assertEquals(expected.toString(), actual.toString());
        }
    }
}
