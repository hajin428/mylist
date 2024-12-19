package com.hajin.mylist.answer.service;

import com.hajin.mylist.answer.dto.AnswerResponseDto;
import com.hajin.mylist.answer.entity.Answer;
import com.hajin.mylist.answer.repository.AnswerRepository;
import com.hajin.mylist.exception.CustomException;
import com.hajin.mylist.exception.ErrorMsg;
import com.hajin.mylist.todo.entity.ToDo;
import com.hajin.mylist.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final ToDoRepository toDoRepository;


    // ToDo에 연관된 Answer 목록 조회
    public List<AnswerResponseDto> getAnswersByToDoId(Long toDoId) {

        // ToDo를 가져옴
        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new CustomException(ErrorMsg.TODO_NOT_FOUND));

        // Answer 목록 조회
        List<Answer> answers = answerRepository.findAllByToDoId(toDoId);

        // Answer가 없을 경우 예외 처리
        if (answers.isEmpty()) {
            throw new CustomException(ErrorMsg.ANSWER_NOT_FOUND);
        }

        // Answer의 content를 리스트로 변환
        List<String> contentList = new ArrayList<>();
        for (Answer answer : answers) {
            contentList.add(answer.getContent());
        }

        // DTO 생성
        return List.of(new AnswerResponseDto(toDo.getTitle(), contentList));
    }


    // AI 응답 JSON 파싱 및 저장
    public void saveAnswersFromGptResponse(JSONObject jsonResponse) {
        // "tasks" 배열을 파싱
        JSONArray tasksArray = jsonResponse.getJSONArray("tasks");

        for (int i = 0; i < tasksArray.length(); i++) {
            JSONObject taskObject = tasksArray.getJSONObject(i);

            // To Do ID와 preparations 추출
            Long id = taskObject.getLong("id");
            JSONArray preparationsArray = taskObject.getJSONArray("preparations");

            // 해당 To Do 찾기
            ToDo toDo = toDoRepository.findById(id)
                    .orElseThrow(() -> new CustomException(ErrorMsg.TODO_NOT_FOUND));

            // 준비사항 저장
            for (int j = 0; j < preparationsArray.length(); j++) {
                JSONObject preparationObject = preparationsArray.getJSONObject(j);
                int key = preparationObject.getInt("task_key");
                String value = preparationObject.getString("value");

                saveAnswer(toDo, key, value);
            }
        }
    }


    // Answer 저장
    private void saveAnswer(ToDo toDo, int taskKey, String content) {
        Answer answer = new Answer(toDo, taskKey, content, LocalDateTime.now());
        answerRepository.save(answer);
    }
}
