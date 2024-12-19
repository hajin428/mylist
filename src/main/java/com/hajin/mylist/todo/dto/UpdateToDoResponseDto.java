package com.hajin.mylist.todo.dto;

import com.hajin.mylist.todo.entity.ToDo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "할 일 전체 수정 응답 데이터")
public class UpdateToDoResponseDto {

    @Schema(description = "수정 결과 메세지", example = "수정이 완료되었습니다.")
    private String message = "수정이 완료되었습니다.";
}
