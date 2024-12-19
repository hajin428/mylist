package com.hajin.mylist.answer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "프롬프트 생성할 날짜 요청 데이터")
public class AnswerGenerationRequestDto {

    @Schema(description = "날짜", example = "2024-12-20")
    private LocalDate date;

}
