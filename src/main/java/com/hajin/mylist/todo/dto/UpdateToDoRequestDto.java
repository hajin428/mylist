package com.hajin.mylist.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
@Schema(description = "할 일 전체 수정 요청 데이터")
public class UpdateToDoRequestDto {

    @Schema(description = "제목")
    private String title;

    @Schema(description = "본문")
    private String description;

    @Schema(description = "실행 날짜", example = "2024-12-12")
    private LocalDate dueDate;

    @Schema(description = "완료 여부")
    private boolean completed;
}