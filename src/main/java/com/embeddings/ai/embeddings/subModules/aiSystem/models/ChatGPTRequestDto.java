package com.embeddings.ai.embeddings.subModules.aiSystem.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChatGPTRequestDto {

    PromptType promptType;
    List<ChatGPTMessage> messages;
}
