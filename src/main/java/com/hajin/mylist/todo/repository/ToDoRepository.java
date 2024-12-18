package com.hajin.mylist.todo.repository;

import com.hajin.mylist.todo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    // 특정 날짜의 To Do 목록 조회
    List<ToDo> findAllByDueDate(LocalDate dueDate);
}
