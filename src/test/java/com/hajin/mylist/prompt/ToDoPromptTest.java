package com.hajin.mylist.prompt;

import com.theokanning.openai.completion.chat.ChatMessage;

import com.hajin.mylist.openAi.client.OpenAiClient;
import com.hajin.mylist.openAi.dto.OpenAiRequestDto;
import com.hajin.mylist.openAi.dto.OpenAiResponseDto;
import com.hajin.mylist.prompt.promptGenerator.ToDoPromptGenerator;
import com.hajin.mylist.todo.entity.ToDo;
import com.hajin.mylist.todo.repository.ToDoRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class ToDoPromptTest {

    @Autowired
    private ToDoPromptGenerator toDoPromptGenerator;

    @Autowired
    private OpenAiClient openAiClient;

    @Autowired
    private ToDoRepository toDoRepository;

    @Test
    public void testToDoPromptGenerationAndOpenAiCall() {

        // Given: 특정 날짜의 To Do 데이터 준비
        LocalDate testDate = LocalDate.of(2024, 12, 15);
        List<ToDo> testToDos = toDoRepository.findAllByDueDate(testDate);

        // 특정 날짜에 데이터가 없을 때
        Assertions.assertFalse(testToDos.isEmpty(), "테스트 To-Do 데이터가 없습니다.");

        // When: 프롬프트 생성
        String prompt = toDoPromptGenerator.generatePromptForDate(testDate);
        System.out.println("\n\nGenerated Prompt:\n\n" + prompt);

        // Then: 프롬프트 유효성 검증
        Assertions.assertNotNull(prompt, "프롬프트가 null입니다.");
        Assertions.assertTrue(prompt.contains("tasks"), "프롬프트에 'tasks' 키가 포함되어 있지 않습니다.");

        try {
            // Step 2: OpenAI API 호출
            OpenAiRequestDto requestDto = new OpenAiRequestDto(
                    "gpt-4-turbo",
                    List.of(new ChatMessage("user", prompt)),
                    1.0 // 창의성 설정
            );

            OpenAiResponseDto response = openAiClient.sendPrompt(requestDto);

            // Step 3: 응답 검증
            Assertions.assertNotNull(response, "OpenAI 응답이 없습니다.");
            Assertions.assertNotNull(response.getChoices(), "OpenAI 응답의 Choices가 null입니다.");
            Assertions.assertFalse(response.getChoices().isEmpty(), "OpenAI 응답의 Choices가 비어 있습니다.");

            String gptResponseContent = response.getChoices().get(0).getMessage().getContent();
            System.out.println("GPT Response:\n" + gptResponseContent);

            // Step 4: 응답 JSON 파싱 및 유효성 검증
            JSONObject jsonResponse = new JSONObject(gptResponseContent);
            Assertions.assertTrue(jsonResponse.has("tasks"), "응답 JSON에 'tasks' 키가 없습니다.");
            assertThat(jsonResponse.getJSONArray("tasks")).isNotEmpty();

        } catch (Exception exception) {
            System.err.println("OpenAI API 호출 실패: " + exception.getMessage());
            Assertions.fail("OpenAI API 호출 중 예외 발생");
        }
    }
}
