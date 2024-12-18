package com.hajin.mylist.prompt.formatter;

import com.hajin.mylist.prompt.mapper.ToDoMapper;
import com.hajin.mylist.todo.entity.ToDo;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
public class ToDoFormatter {

    // To Do 데이터를 포맷팅하여 문자열 반환
    public String print(ToDo toDo, Locale locale) {

        Map<String, Object> toDoMap = ToDoMapper.convertToMap(toDo);
        StringBuilder sb = new StringBuilder();

        toDoMap.forEach((key, value) -> {
            sb.append(key).append(": ").append(value).append("\n");
        });

        return sb.toString();
    }
}
