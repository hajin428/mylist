package com.hajin.mylist.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "삭제 응답 데이터")
public class DeleteToDoResponseDto {

    @Schema(description = "삭제 결과 메세지", example = "항목이 삭제되었습니다.")
    private String message;

    public DeleteToDoResponseDto() {
        this.message = "항목이 삭제되었습니다.";
    }
}
