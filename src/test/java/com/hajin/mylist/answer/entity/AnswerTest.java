package com.hajin.mylist.answer.entity;

import com.hajin.mylist.todo.entity.ToDo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class AnswerTest {

    @Test
    @DisplayName("Answer 생성자 테스트 - ToDo와 key, content, createdAt을 통한 생성")
    void testConstructorWithParameters() {
        // Given: Mocked ToDo와 초기 데이터 준비
        ToDo toDo = mock(ToDo.class);
        int taskKey = 1;
        String content = "AI 응답 내용";
        LocalDateTime createdAt = LocalDateTime.of(2024, 12, 19, 10, 0);

        // When: Answer 객체 생성
        Answer answer = new Answer(toDo, taskKey, content, createdAt);

        // Then: 필드 값 검증
        assertEquals(toDo, answer.getToDo());
        assertEquals(taskKey, answer.getTaskKey());
        assertEquals(content, answer.getContent());
        assertEquals(createdAt, answer.getCreatedAt());
    }

    @Test
    @DisplayName("Answer 기본 생성자 테스트")
    void testDefaultConstructor() {
        // Given & When: Answer 기본 생성자 호출
        Answer answer = new Answer();

        // Then: 기본값 검증
        assertEquals(null, answer.getToDo());
        assertEquals(0, answer.getTaskKey());
        assertEquals(null, answer.getContent());
        assertEquals(null, answer.getCreatedAt());
    }
}
