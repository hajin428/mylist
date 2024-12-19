package com.hajin.mylist.answer.entity;

import com.hajin.mylist.todo.entity.ToDo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class AnswerTest {

    @Test
    @DisplayName("Answer 생성자 테스트")
    void testConstructorWithParameters() {

        // Given
        ToDo toDo = mock(ToDo.class);
        int taskKey = 1;
        String content = "AI 응답 내용";
        LocalDateTime createdAt = LocalDateTime.of(2024, 12, 19, 10, 0);

        // When
        Answer answer = new Answer(toDo, taskKey, content, createdAt);

        // Then
        assertEquals(toDo, answer.getToDo());
        assertEquals(taskKey, answer.getTaskKey());
        assertEquals(content, answer.getContent());
        assertEquals(createdAt, answer.getCreatedAt());
    }

    @Test
    @DisplayName("Answer 기본 생성자 테스트")
    void testDefaultConstructor() {

        // Given & When
        Answer answer = new Answer();

        // Then
        assertEquals(null, answer.getToDo());
        assertEquals(0, answer.getTaskKey());
        assertEquals(null, answer.getContent());
        assertEquals(null, answer.getCreatedAt());
    }
}
