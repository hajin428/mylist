package com.hajin.mylist.todo.service;

import com.hajin.mylist.exception.CustomException;
import com.hajin.mylist.exception.ErrorMsg;
import com.hajin.mylist.todo.dto.*;
import com.hajin.mylist.todo.entity.ToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import com.hajin.mylist.todo.repository.ToDoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    // 할 일 작성
    public CreateToDoResponseDto createToDo(CreateToDoRequestDto requestDto) {

        ToDo toDo = new ToDo (requestDto);

        toDoRepository.save(toDo);

        return new CreateToDoResponseDto();
    }


    // 할 일 수정
    public UpdateToDoResponseDto updateToDo(Long id, UpdateToDoRequestDto requestDto) {

        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMsg.TODO_NOT_FOUND));

        toDo.update(requestDto);

        toDoRepository.save(toDo);

        return new UpdateToDoResponseDto();
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
    @Cacheable(value = "getAllToDos")
    public Page<GetAllToDoResponseDto> getAllToDos(Pageable pageable) {

        // repository에서 페이지별 목록 찾기
        Page<ToDo> toDoPage = toDoRepository.findAll(pageable);

        // 목록이 비어 있는 경우 예외 처리
        if (toDoPage.isEmpty()) {
            throw new CustomException(ErrorMsg.TODO_NOT_FOUND);
        }

        // DTO로 변환
        return toDoPage.map(GetAllToDoResponseDto::new);
    }


    // 특정 날짜의 할 일 목록 조회
    @Cacheable(value = "getToDosByDate", key = "#date.toString()")
    public List<GetToDoByDateResponseDto> getToDosByDate(LocalDate date) {

        // 특정 날짜의 To Do 목록만 조회
        List<ToDo> toDos = toDoRepository.findAllByDueDate(date);

        // 목록이 비어 있으면 예외 발생
        if (toDos.isEmpty()) {
            throw new CustomException(ErrorMsg.TODO_NOT_FOUND_FOR_DATE);
        }

        List<GetToDoByDateResponseDto> responseDtos = new ArrayList<>();

        for (ToDo toDo : toDos) {
            responseDtos.add(new GetToDoByDateResponseDto(toDo));
        }

        return responseDtos;
    }


    // 완료 여부 수정
    public CompletedUpdateResponseDto updateToDoCompleted(Long id, CompletedUpdateRequestDto requestDto) {

        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMsg.TODO_NOT_FOUND));

        toDo.setCompleted(requestDto);

        toDoRepository.save(toDo);

        return new CompletedUpdateResponseDto();
    }
}
