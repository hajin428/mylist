package com.hajin.mylist.prompt.mapper;

import com.hajin.mylist.todo.entity.ToDo;

import java.util.HashMap;
import java.util.Map;

public class ToDoMapper {

    // To Do 데이터를 Map으로 변환
    public static Map<String, Object> convertToMap(ToDo toDo) {

        Map<String, Object> map = new HashMap<>();

        map.put("제목", toDo.getTitle());
        map.put("설명", toDo.getDescription());

        return map;
    }
}
