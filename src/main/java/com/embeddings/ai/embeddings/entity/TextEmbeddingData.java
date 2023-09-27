package com.embeddings.ai.embeddings.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Builder
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class TextEmbeddingData {
    private Data[] data;
    private String model;
    private String object;
    private Usage usage;

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    // Inner classes for nested objects

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Data {
        private Double[] embedding;
        private int index;
        private String object;

        public Double[] getEmbedding() {
            return embedding;
        }

        public void setEmbedding(Double[] embedding) {
            this.embedding = embedding;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }
    }

    @Builder
    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;
        @JsonProperty("total_tokens")
        private int totalTokens;

        public int getPromptTokens() {
            return promptTokens;
        }

        public void setPromptTokens(int promptTokens) {
            this.promptTokens = promptTokens;
        }

        public int getTotalTokens() {
            return totalTokens;
        }

        public void setTotalTokens(int totalTokens) {
            this.totalTokens = totalTokens;
        }
    }
}
