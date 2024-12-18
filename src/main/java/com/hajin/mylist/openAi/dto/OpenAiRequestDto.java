package com.hajin.mylist.openAi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OpenAiRequestDto {

    @JsonProperty("model")
    private String model; // GPT 모델 "gpt-4-turbo"

    @JsonProperty("messages")
    private List<ChatMessage> messages; // Chat Completion 메시지 리스트

    @JsonProperty("temperature")
    private Double temperature; // 창의성 조절 값 (0.0 ~ 1.0)

}
