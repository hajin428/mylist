package com.hajin.mylist.prompt.promptGenerator;

import com.hajin.mylist.exception.CustomException;
import com.hajin.mylist.exception.ErrorMsg;
import com.hajin.mylist.prompt.formatter.ToDoFormatter;
import com.hajin.mylist.todo.entity.ToDo;
import com.hajin.mylist.todo.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoPromptGeneratorTest {

    @InjectMocks
    private ToDoPromptGenerator toDoPromptGenerator;

    @Mock
    private ToDoRepository toDoRepository;

    @Mock
    private ToDoFormatter toDoFormatter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("프롬프트 생성 성공 테스트")
    void generatePromptForDate_Success() {
        // Given
        LocalDate testDate = LocalDate.of(2024, 12, 20);
        ToDo mockToDo1 = mock(ToDo.class);
        ToDo mockToDo2 = mock(ToDo.class);

        when(toDoRepository.findAllByDueDate(testDate)).thenReturn(List.of(mockToDo1, mockToDo2));
        when(toDoFormatter.print(mockToDo1, Locale.getDefault())).thenReturn("      {\"id\": 1, \"title\": \"Test 1\"}");
        when(toDoFormatter.print(mockToDo2, Locale.getDefault())).thenReturn("      {\"id\": 2, \"title\": \"Test 2\"}");

        // When
        String prompt = toDoPromptGenerator.generatePromptForDate(testDate);

        // Then
        assertNotNull(prompt, "Prompt should not be null.");
        assertTrue(prompt.contains("\"tasks\": ["));
        assertTrue(prompt.contains("{\"id\": 1, \"title\": \"Test 1\"}"));
        assertTrue(prompt.contains("{\"id\": 2, \"title\": \"Test 2\"}"));

        // Print generated prompt
        System.out.println("Generated Prompt:\n" + prompt);

        verify(toDoRepository, times(1)).findAllByDueDate(testDate);
        verify(toDoFormatter, times(2)).print(any(ToDo.class), eq(Locale.getDefault()));
    }

    @Test
    @DisplayName("프롬프트 생성 실패 테스트 - 날짜에 해당하는 ToDo 없음")
    void generatePromptForDate_Failure_NoToDos() {
        // Given
        LocalDate testDate = LocalDate.of(2024, 12, 20);
        when(toDoRepository.findAllByDueDate(testDate)).thenReturn(List.of());

        // When & Then
        CustomException exception = assertThrows(CustomException.class,
                () -> toDoPromptGenerator.generatePromptForDate(testDate));

        assertEquals(ErrorMsg.TODO_NOT_FOUND_FOR_DATE.getCode(), exception.getCode());

        verify(toDoRepository, times(1)).findAllByDueDate(testDate);
        verify(toDoFormatter, never()).print(any(ToDo.class), eq(Locale.getDefault()));
    }
}
