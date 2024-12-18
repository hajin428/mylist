package com.hajin.mylist.openAi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OpenAiResponseDto {

    @JsonProperty("choices")
    private List<Choice> choices;

    @Getter
    @NoArgsConstructor
    public static class Choice {
        @JsonProperty("message")
        private Message message;
    }

    @Getter
    @NoArgsConstructor
    public static class Message {

        @JsonProperty("content")
        private String content;
    }
}

