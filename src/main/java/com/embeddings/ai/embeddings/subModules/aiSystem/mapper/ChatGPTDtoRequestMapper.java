package com.embeddings.ai.embeddings.subModules.aiSystem.mapper;

import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTMessage;
import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTRequest;
import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTRequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ChatGPTDtoRequestMapper implements Function<ChatGPTRequestDto, ChatGPTRequest> {
    @Override
    public ChatGPTRequest apply(ChatGPTRequestDto chatGPTRequestDto) {
        List<ChatGPTMessage> chatGPTMessageList=new ArrayList<>();
        chatGPTMessageList.add(ChatGPTMessage
                .builder()
                        .role("system")
                        .content(chatGPTRequestDto.getPromptType().toString())
                .build()
        );
        chatGPTMessageList.addAll(chatGPTRequestDto.getMessages());
        return ChatGPTRequest
                .builder()
                .messages(chatGPTMessageList)
                .build();
    }
}
