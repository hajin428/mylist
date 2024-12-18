package com.hajin.mylist.openAi;

import com.hajin.mylist.openAi.client.OpenAiClient;
import com.hajin.mylist.openAi.dto.OpenAiRequestDto;
import com.hajin.mylist.openAi.dto.OpenAiResponseDto;
import com.theokanning.openai.completion.chat.ChatMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OpenAiClientTest {

    @Autowired
    private OpenAiClient openAiClient;

    @Test
    @DisplayName("OpenAI API 호출 테스트 - GPT 응답 확인")
    void testOpenAiApiCall() {

        // Given: 요청 데이터 준비
        ChatMessage systemMessage = new ChatMessage("system", "You are a helpful assistant.");
        ChatMessage userMessage = new ChatMessage("user",
                "내일 할 일: 영화 보기 (CGV 대구, 위키드, 4시), 코딩 테스트 공부 (프로그래머스), 강아지 산책\n" +
                        "위의 목록은 내가 내일 할 일들이야. 내일 할 일을 위해 내가 준비해야 될 것을 알려줘. \n" +
                        "답변은 JSON 형태로 응답해. 다른 설명은 붙이지마. JSON으로만 대답해.");
        OpenAiRequestDto requestDto = new OpenAiRequestDto(
                "gpt-4-turbo",
                List.of(systemMessage, userMessage)
        );

        // When: OpenAI API 호출
        OpenAiResponseDto response = openAiClient.sendPrompt(requestDto);

        // Then: 응답 검증
        assertNotNull(response);
        assertNotNull(response.getChoices());
        assertNotNull(response.getChoices().get(0).getMessage().getContent());

        // 응답 내용 출력
        String content = response.getChoices().get(0).getMessage().getContent();
        System.out.println("OpenAI 응답: " + content);
    }
}
