package com.embeddings.ai.embeddings.subModules.aiSystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatGPTUsageData {
    @JsonProperty("completion_tokens")
    private int completionTokens;
    @JsonProperty("prompt_tokens")
    private int promptTokens;
    @JsonProperty("total_tokens")
    private int totalTokens;
}
