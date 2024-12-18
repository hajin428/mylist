package com.hajin.mylist.answer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "To Do 항목 한 개의 AI 응답 조회 데이터")
public class AnswerResponseDto {

    @Schema(description = "제목")
    private String title;

    @Schema(description = "AI 응답 리스트")
    private List<String> content;

    public AnswerResponseDto(String title, List<String> content) {
        this.title = title;
        this.content = content;
    }

}
