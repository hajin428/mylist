package com.hajin.mylist.prompt.promptGenerator;

import com.hajin.mylist.exception.CustomException;
import com.hajin.mylist.exception.ErrorMsg;
import com.hajin.mylist.prompt.formatter.ToDoFormatter;
import com.hajin.mylist.todo.entity.ToDo;
import com.hajin.mylist.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Component("toDoPromptGenerator")
@RequiredArgsConstructor
public class ToDoPromptGenerator {

    private final ToDoRepository toDoRepository;
    private final ToDoFormatter toDoFormatter;


    public String generatePromptForDate(LocalDate date) {

        // 특정 날짜의 To Do 목록 조회
        List<ToDo> toDoList = toDoRepository.findAllByDueDate(date);

        if (toDoList.isEmpty()) {
            throw new CustomException(ErrorMsg.TODO_NOT_FOUND_FOR_DATE);
        }

        // 프롬프트 시작 문구
        StringBuilder promptBuilder = new StringBuilder(
                "아래의 목록은 내가 해야 할 일들이야.\n" +
                "너는 내가 해야 할 일들을 보고 어떤 준비가 필요한지 도와줘야돼.\n\n"
        );

        promptBuilder.append("{\n");
        promptBuilder.append("  \"tasks\": [\n");

        // 포맷터를 사용해 ToDo를 JSON 형식으로 변환
        for (int i = 0; i < toDoList.size(); i++) {
            ToDo toDo = toDoList.get(i);

            // 포맷터를 사용해 ToDo를 JSON 형식으로 포맷팅
            String formattedToDo = toDoFormatter.print(toDo, Locale.getDefault());
            promptBuilder.append(formattedToDo);

            if (i < toDoList.size() - 1) {
                promptBuilder.append(",");
            }

            promptBuilder.append("\n");
        }

        promptBuilder.append("  ]\n");
        promptBuilder.append("}\n\n");
        promptBuilder.append(
                "할 일을 잘 수행하기 위해 내가 준비해야 될 것들을 알려줘. \n" +
                "\n응답 내용의 예시: 예매 내역 확인하기, 영화 시작 전에 도착하기 위해 출발 시간 체크, 회의 내용 확인하기," +
                "면접 답변 스크립트 작성하기, 예약 가능한 도서 목록 확인하기 \n\n" +
                "응답의 예시를 참고해서 내가 해야 할 일들을 준비할 수 있게 도와줘.\n" +
                "응답은 간결하고 분명한 것들로 작성해. 그리고 JSON 형식으로 대답해. \n\n" +
                "다시 한번 말할게. 답변은 반드시 JSON 형식으로 대답해. 다른 사족은 붙이지마. " +
                "JSON만 응답해. 코드 블록 표시도 하지마. 제공한 JSON의 key값 변경도 하지마 있는 그대로 사용해서 대답해.\n");

        return promptBuilder.toString();
    }
}
