package com.embeddings.ai.embeddings.subModules.aiSystem.mapper;


import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTResponse;
import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTResponseDto;

import java.util.function.Function;

public class ChatGPTDtoResponseMapper implements Function<ChatGPTResponse, ChatGPTResponseDto> {
    @Override
    public ChatGPTResponseDto apply(ChatGPTResponse chatGPTResponse) {
        return ChatGPTResponseDto
                .builder()
                .messageResponse(chatGPTResponse.getChoices().get(0).getMessage())
                .build();
    }
}
