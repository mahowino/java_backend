package com.embeddings.ai.embeddings.subModules.aiSystem.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
public class ChatGPTMessage {
    private  String role;
    private  String content;
}
