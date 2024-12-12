package com.hajin.mylist.todo.dto;

import com.hajin.mylist.todo.entity.ToDo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "완료 여부 수정 응답 데이터")
public class CompletedUpdateResponseDto {

    @Schema(description = "완료 여부 수정 결과 메세지", example = "수정이 완료되었습니다.")
    private String messege;

    public CompletedUpdateResponseDto (ToDo todo) {
        this.messege = "수정이 완료되었습니다.";
    }
}
