package com.hajin.mylist.todo.controller;

import com.hajin.mylist.todo.dto.*;
import com.hajin.mylist.todo.service.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ToDo API", description = "할 일 관리 API")
@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    // 할 일 작성
    @Operation(summary = "To Do 작성", description = "새로운 항목을 작성합니다.")
    @PostMapping
    public ResponseEntity<CreateToDoResponseDto> createToDo(
            @RequestBody CreateToDoRequestDto requestDto) {

        CreateToDoResponseDto responseDto = toDoService.createToDo(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    // 할 일 수정
    @Operation(summary = "To Do 수정", description = "항목을 전체 내용을 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<UpdateToDoResponseDto> updateToDo(
            @RequestParam Long id,
            @RequestBody UpdateToDoRequestDto requestDto) {

        UpdateToDoResponseDto responseDto = toDoService.updateToDo(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    // 할 일 단건 조회
    @Operation(summary = "To Do 단건 조회", description = "항목을 단건 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<GetToDoResponseDto> getToDoById(@RequestParam Long id) {

        GetToDoResponseDto responseDto = toDoService.getToDoById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    // 할 일 삭제
    @Operation(summary = "To Do 삭제", description = "항목을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteToDoResponseDto> deleteToDo(@RequestParam Long id) {

        DeleteToDoResponseDto responseDto = toDoService.deleteToDo(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    // 전체 목록 조회
    @Operation(summary = "To Do 전체 조회", description = "전체 항목을 조회합니다.")
    @GetMapping("/alltodo")
    public ResponseEntity<Page<GetAllToDoResponseDto>> getAllToDos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<GetAllToDoResponseDto> responseDtos = toDoService.getAllToDos(pageable);

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }


    // 특정 날짜의 할 일 목록 조회
    @Operation(summary = "특정 날짜의 To Do 조회", description = "특정 날짜에 등록된 할 일 목록을 조회합니다.")
    @GetMapping("/bydate")
    public ResponseEntity<List<GetToDoByDateResponseDto>> getToDosByDate(
            @RequestBody GetToDoByDateRequestDto requestDto) {

        List<GetToDoByDateResponseDto> responseDtos = toDoService.getToDosByDate(requestDto);
        return ResponseEntity.ok(responseDtos);
    }


    // 완료 여부 수정
    @Operation(summary = "완료 여부 수정", description = "항목에 대한 완료 여부만 수정합니다.")
    @PatchMapping("/{id}/completed")
    public ResponseEntity<CompletedUpdateResponseDto> updateToDoCompleted(
            @RequestParam Long id,
            @RequestBody CompletedUpdateRequestDto requestDto) {

        CompletedUpdateResponseDto responseDto = toDoService.updateToDoCompleted(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}