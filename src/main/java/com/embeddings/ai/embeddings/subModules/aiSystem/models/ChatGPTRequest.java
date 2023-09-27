package com.embeddings.ai.embeddings.subModules.aiSystem.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChatGPTRequest {

    //todo:make this an environment variabke
    private final String model = "gpt-3.5-turbo";

    private final List<ChatGPTMessage> messages;
}
