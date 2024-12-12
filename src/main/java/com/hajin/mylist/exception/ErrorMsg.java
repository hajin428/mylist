package com.hajin.mylist.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMsg {

    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, 1000, "할 일을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String details;
}
