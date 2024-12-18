package com.hajin.mylist.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMsg {

    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, 1000, "할 일을 찾을 수 없습니다."),
    TODO_NOT_FOUND_FOR_DATE (HttpStatus.NOT_FOUND, 1001, "해당 날짜의 To Do 목록이 없습니다."),
    ANSWER_NOT_FOUND(HttpStatus.NOT_FOUND, 1002, "AI 응답을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String details;
}
