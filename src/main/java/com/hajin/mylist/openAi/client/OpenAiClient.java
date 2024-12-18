package com.hajin.mylist.openAi.client;

import com.hajin.mylist.openAi.dto.OpenAiRequestDto;
import com.hajin.mylist.openAi.dto.OpenAiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OpenAiClient {

    @Value("${openai.api.url}")
    private String openaiApiUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public OpenAiResponseDto sendPrompt(OpenAiRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<OpenAiRequestDto> entity = new HttpEntity<>(requestDto, headers);

        ResponseEntity<OpenAiResponseDto> response = restTemplate.exchange(
                openaiApiUrl,
                HttpMethod.POST,
                entity,
                OpenAiResponseDto.class
        );

        return response.getBody();

    }
}