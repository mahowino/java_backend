package com.embeddings.ai.embeddings.subModules.aiSystem.controller;

import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTRequestDto;
import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTResponseDto;
import com.embeddings.ai.embeddings.subModules.aiSystem.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    public ChatGPTResponseDto getResponseFromAI(ChatGPTRequestDto chatGPTRequestDto) throws Exception {
        return aiService.sendChatRequest(chatGPTRequestDto);
    }
}
