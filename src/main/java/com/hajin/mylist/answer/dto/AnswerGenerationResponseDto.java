package com.hajin.mylist.answer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "AI 응답 저장 성공 메세지", example = "응답이 성공적으로 저장되었습니다.")
public class AnswerGenerationResponseDto {

    private String message = "응답이 성공적으로 저장되었습니다.";
}
