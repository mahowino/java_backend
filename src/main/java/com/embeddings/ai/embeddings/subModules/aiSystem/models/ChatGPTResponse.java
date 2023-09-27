package com.embeddings.ai.embeddings.subModules.aiSystem.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class ChatGPTResponse {
    private List<ChatGPTChoicesData> choices;
    private long created;
    private String id;
    private String model;
    private String object;
    private ChatGPTUsageData usage;
}
