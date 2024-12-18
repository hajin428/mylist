package com.hajin.mylist.prompt.mapper;

import com.hajin.mylist.todo.entity.ToDo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ToDoMapper {

    public static Map<String, Object> convertToMap(ToDo toDo) {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", toDo.getId());
        map.put("제목", toDo.getTitle());
        map.put("설명", toDo.getDescription());

        map.put("preparations", List.of(
                createPreparationMap(1, ""),
                createPreparationMap(2, ""),
                createPreparationMap(3, "")
        ));

        return map;
    }

    private static Map<String, Object> createPreparationMap(int taskKey, String value) {
        Map<String, Object> preparationMap = new LinkedHashMap<>();
        preparationMap.put("task_key", taskKey);
        preparationMap.put("value", value);
        return preparationMap;
    }

}
