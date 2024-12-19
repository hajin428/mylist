package com.hajin.mylist.answer.controller;

import com.hajin.mylist.answer.dto.AnswerResponseDto;
import com.hajin.mylist.answer.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    // 특정 ToDo에 연관된 Answer 목록 조회
    @Operation(summary = "특정 To Do 항목의 AI 응답 조회", description = "한 개의 할 일 목록에 생성된 AI 응답 리스트를 조회합니다.")
    @GetMapping("/{toDoId}")
    public ResponseEntity<List<AnswerResponseDto>> getAnswersByToDoId(@RequestParam Long toDoId) {
        List<AnswerResponseDto> responseDtos = answerService.getAnswersByToDoId(toDoId);
        return ResponseEntity.ok(responseDtos);
    }
}
