package com.hajin.mylist.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "작성 응답 데이터")
public class CreateToDoResponseDto {

    @Schema(description = "작성 결과 메세지", example = "작성이 완료되었습니다.")
    private String messege = "작성이 완료되었습니다.";
}
