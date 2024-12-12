package com.hajin.mylist.todo.service;

import com.hajin.mylist.exception.CustomException;
import com.hajin.mylist.exception.ErrorMsg;
import com.hajin.mylist.todo.dto.*;
import com.hajin.mylist.todo.entity.ToDo;
import com.hajin.mylist.todo.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    // 할 일 작성
    public CreateToDoResponseDto createToDo(CreateToDoRequestDto requestDto) {

        ToDo toDo = new ToDo (requestDto);

        toDoRepository.save(toDo);

        return new CreateToDoResponseDto(toDo);
    }


    // 할 일 수정
    public UpdateToDoResponseDto updateToDo(Long id, UpdateToDoRequestDto requestDto) {

        // id로 항목 찾기
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMsg.TODO_NOT_FOUND));

        toDo.update(requestDto);

        toDoRepository.save(toDo);

        return new UpdateToDoResponseDto(toDo);
    }


    // 할 일 단건 조회
    public GetToDoResponseDto getToDoById(Long id) {

        // id로 항목 찾기
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMsg.TODO_NOT_FOUND));

        return new GetToDoResponseDto(toDo);
    }


    // 할 일 삭제
    public DeleteToDoResponseDto deleteToDo(Long id) {

        // id로 항목 찾기
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMsg.TODO_NOT_FOUND));

        toDoRepository.delete(toDo);

        return new DeleteToDoResponseDto();
    }


    // 할 일 목록 전체 조회
    public List<GetAllToDoResponseDto> getAllToDos() {

        // repository에서 전체 목록 찾기
        List<ToDo> toDos = toDoRepository.findAll();

        List<GetAllToDoResponseDto> responseDtos = new ArrayList<>();

        // 조회한 데이터 DTO로 변환
        for (ToDo toDo : toDos) {
            responseDtos.add(new GetAllToDoResponseDto(toDo));
        }

        return responseDtos;
    }


    // 완료 여부 수정
    public CompletedUpdateResponseDto updateToDoCompleted(Long id, CompletedUpdateRequestDto requestDto) {

        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMsg.TODO_NOT_FOUND));

        toDo.setCompleted(requestDto);

        toDoRepository.save(toDo);

        return new CompletedUpdateResponseDto(toDo);
    }
}
