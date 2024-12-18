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
        ChatMessage userMessage = new ChatMessage("user", "안녕하세요.");
        OpenAiRequestDto requestDto = new OpenAiRequestDto(
                "gpt-4-turbo",
                List.of(systemMessage, userMessage),
                0.8 // 창의성 값 설정
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
