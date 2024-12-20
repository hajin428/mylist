package com.hajin.mylist.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMsg {

    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, 1000, "할 일을 찾을 수 없습니다."),
    TODO_NOT_FOUND_FOR_DATE (HttpStatus.NOT_FOUND, 1001, "해당 날짜의 To Do 목록이 없습니다."),
    ANSWER_NOT_FOUND(HttpStatus.NOT_FOUND, 1002, "AI 응답을 찾을 수 없습니다."),
    INVALID_OPENAI_RESPONSE(HttpStatus.BAD_REQUEST, 1003, "유효하지 않은 OpenAI 응답입니다."),
    JSON_PARSING_FAILED(HttpStatus.BAD_REQUEST, 1004, "JSON 파싱에 실패했습니다."),

    PROMPT_GENERATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 2000, "프롬프트 생성에 실패했습니다."),
    OPENAI_API_CALL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 2001, "OpenAI API 호출에 실패했습니다."),
    DB_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 1007, "데이터 저장에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String details;
}
