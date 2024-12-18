package com.hajin.mylist.todo.entity;

import com.hajin.mylist.answer.entity.Answer;
import com.hajin.mylist.todo.dto.CompletedUpdateRequestDto;
import com.hajin.mylist.todo.dto.CreateToDoRequestDto;
import com.hajin.mylist.todo.dto.UpdateToDoRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todo", indexes = {
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_due_date", columnList = "dueDate")
})
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false)
    private String description; // 본문

    @Column(nullable = false)
    private LocalDate dueDate; // 실행 날짜

    @Column(nullable = false)
    private boolean completed; // 완료 여부

    @OneToMany(mappedBy = "toDo", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();


    // 할 일 작성
    public ToDo(CreateToDoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.dueDate = requestDto.getDueDate();
        this.completed = false;
    }

    // 상태 변경 메서드
    public void update(UpdateToDoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.dueDate = requestDto.getDueDate();
        this.completed = false;
    }

    // 완료 여부만 수정
    public void setCompleted(CompletedUpdateRequestDto requestDto) {
        this.completed = requestDto.isCompleted();
    }
}
