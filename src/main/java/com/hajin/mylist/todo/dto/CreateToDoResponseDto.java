package com.hajin.mylist.todo.dto;

import com.hajin.mylist.todo.entity.ToDo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "작성 응답 데이터")
public class CreateToDoResponseDto {

    @Schema(description = "작성 결과 메세지", example = "작성이 완료되었습니다.")
    private String messege;

    public CreateToDoResponseDto (ToDo todo) {
        this.messege = "작성이 완료되었습니다.";
    }
}
