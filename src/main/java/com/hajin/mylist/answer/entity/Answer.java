package com.hajin.mylist.answer.entity;

import com.hajin.mylist.todo.entity.ToDo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private ToDo toDo; // 연관된 To Do

    @Column(name = "task_key", nullable = false)
    private int taskKey; // preparation의 key

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // AI 응답 내용

    @Column(nullable = false)
    private LocalDateTime createdAt; // 생성 일자


    public Answer(ToDo toDo, int taskKey, String content, LocalDateTime createdAt) {
        this.toDo = toDo;
        this.taskKey = taskKey;
        this.content = content;
        this.createdAt = createdAt;
    }
}
