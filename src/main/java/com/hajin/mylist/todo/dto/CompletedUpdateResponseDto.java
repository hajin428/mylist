package com.hajin.mylist.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "완료 여부 수정 응답 데이터")
public class CompletedUpdateResponseDto {

    @Schema(description = "완료 여부 수정 결과 메세지", example = "수정이 완료되었습니다.")
    private String messege = "수정이 완료되었습니다.";

}
