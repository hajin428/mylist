package com.hajin.mylist.todo.dto;

import com.hajin.mylist.todo.entity.ToDo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Schema(description = "특정 날짜 기반 To Do 응답 데이터")
public class GetToDoByDateResponseDto {

    @Schema(description = "제목")
    private String title;

    @Schema(description = "본문")
    private String description;

    @Schema(description = "실행 날짜", example = "2024-12-12")
    private LocalDate dueDate;

    @Schema(description = "완료 여부")
    private boolean completed;

    public GetToDoByDateResponseDto(ToDo toDo) {
        this.title = toDo.getTitle();
        this.description = toDo.getDescription();
        this.dueDate = toDo.getDueDate();
        this.completed = toDo.isCompleted();
    }
}
