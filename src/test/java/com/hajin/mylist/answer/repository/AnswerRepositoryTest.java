package com.hajin.mylist.answer.repository;

import com.hajin.mylist.answer.entity.Answer;
import com.hajin.mylist.todo.entity.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AnswerRepositoryTest {

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private ToDo toDo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("특정 ToDo ID로 Answer 목록 조회 테스트")
    void testFindAllByToDoId() {
        // Given
        Long toDoId = 1L;
        Answer answer1 = new Answer(toDo, 1, "Answer Content 1", LocalDateTime.now());
        Answer answer2 = new Answer(toDo, 2, "Answer Content 2", LocalDateTime.now());
        List<Answer> mockAnswers = Arrays.asList(answer1, answer2);
        when(answerRepository.findAllByToDoId(toDoId)).thenReturn(mockAnswers);

        // When
        List<Answer> answers = answerRepository.findAllByToDoId(toDoId);

        // Then
        assertEquals(2, answers.size());
        assertEquals("Answer Content 1", answers.get(0).getContent());
        assertEquals("Answer Content 2", answers.get(1).getContent());

        // Verify that the repository method was called once
        verify(answerRepository, times(1)).findAllByToDoId(toDoId);
    }
}
