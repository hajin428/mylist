package com.hajin.mylist.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "완료 여부 수정 요청 데이터")
public class CompletedUpdateRequestDto {

    @Schema(description = "완료 여부")
    private boolean completed; // 완료 여부
}
