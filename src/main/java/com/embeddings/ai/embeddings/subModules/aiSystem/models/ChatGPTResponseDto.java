package com.embeddings.ai.embeddings.subModules.aiSystem.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class ChatGPTResponseDto {
    private final ChatGPTMessage messageResponse;
}
