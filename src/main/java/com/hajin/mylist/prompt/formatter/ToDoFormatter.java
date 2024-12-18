package com.hajin.mylist.prompt.formatter;

import com.hajin.mylist.prompt.mapper.ToDoMapper;
import com.hajin.mylist.todo.entity.ToDo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
public class ToDoFormatter {

    public String print(ToDo toDo, Locale locale) {
        Map<String, Object> toDoMap = ToDoMapper.convertToMap(toDo);

        // JSON 형식으로 변환
        StringBuilder sb = new StringBuilder();

        sb.append("    {\n");
        toDoMap.forEach((key, value) -> {
            if (value instanceof String) {
                sb.append("      \"").append(key).append("\": \"").append(value).append("\",\n");
            } else if (value instanceof List) {
                sb.append("      \"").append(key).append("\": ").append(value).append("\n");
            } else {
                sb.append("      \"").append(key).append("\": ").append(value).append(",\n");
            }
        });
        sb.append("    }");

        return sb.toString();
    }

}
