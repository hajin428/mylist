package com.hajin.mylist.answer.controller;

import com.hajin.mylist.answer.dto.AnswerGenerationRequestDto;
import com.hajin.mylist.answer.dto.AnswerGenerationResponseDto;
import com.hajin.mylist.answer.dto.AnswerResponseDto;
import com.hajin.mylist.answer.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    // 특정 ToDo에 연관된 Answer 목록 조회
    @Operation(summary = "특정 To Do 항목의 AI 응답 조회", description = "한 개의 할 일 목록에 생성된 AI 응답 리스트를 조회합니다.")
    @GetMapping("/{toDoId}")
    public ResponseEntity<List<AnswerResponseDto>> getAnswersByToDoId(@RequestParam Long toDoId) {
        List<AnswerResponseDto> responseDtos = answerService.getAnswersByToDoId(toDoId);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    // 프롬프트 생성 및 AI 응답 저장
    @Operation(summary = "프롬프트 생성 및 OpenAI 호출", description = "특정 날짜의 To Do 데이터 기반으로 프롬프트 생성 및 AI 응답 저장")
    @PostMapping("/generate")
    public ResponseEntity<AnswerGenerationResponseDto> generateAndSaveAnswers(@RequestBody AnswerGenerationRequestDto requestDto) {
        AnswerGenerationResponseDto responseDto = answerService.generateAndSaveAnswers(requestDto.getDate());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
