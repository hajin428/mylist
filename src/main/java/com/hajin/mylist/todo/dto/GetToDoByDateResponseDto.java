package com.hajin.mylist.todo.dto;

import com.hajin.mylist.todo.entity.ToDo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class GetToDoByDateResponseDto {

    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    public GetToDoByDateResponseDto(ToDo toDo) {
        this.title = toDo.getTitle();
        this.description = toDo.getDescription();
        this.dueDate = toDo.getDueDate();
        this.completed = toDo.isCompleted();
    }

}
