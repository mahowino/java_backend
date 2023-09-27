package com.embeddings.ai.embeddings.subModules.aiSystem.service;


import com.embeddings.ai.embeddings.subModules.aiSystem.mapper.ChatGPTDtoRequestMapper;
import com.embeddings.ai.embeddings.subModules.aiSystem.mapper.ChatGPTDtoResponseMapper;
import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTRequest;
import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTRequestDto;
import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTResponseDto;
import com.embeddings.ai.embeddings.subModules.aiSystem.util.ChatGPTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {


    private final ChatGPTUtil chatGPTUtil;

    private final ChatGPTDtoRequestMapper chatGPTDtoRequestMapper;
    private final ChatGPTDtoResponseMapper chatGPTDtoResponseMapper;



    public ChatGPTResponseDto sendChatRequest(ChatGPTRequestDto chatGPTRequestDto) throws Exception {

        ChatGPTRequest chatRequest=chatGPTDtoRequestMapper.apply(chatGPTRequestDto);

        return chatGPTDtoResponseMapper.apply(chatGPTUtil.readResponse(chatRequest));


    }


}
