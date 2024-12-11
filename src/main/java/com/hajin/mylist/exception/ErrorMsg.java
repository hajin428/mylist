package com.hajin.mylist.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMsg {

    BAD_REQUEST(HttpStatus.BAD_REQUEST,1000, "잘못된 요청입니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String details;
}
