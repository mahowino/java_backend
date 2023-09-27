package com.embeddings.ai.embeddings.subModules.aiSystem.models;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTChoicesData {
    private String finishReason;
    private int index;
    private ChatGPTMessage message;
}
