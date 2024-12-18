package com.hajin.mylist.todo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class GetToDoByDateRequestDto {

    private LocalDate date; // 조회할 날짜
}
