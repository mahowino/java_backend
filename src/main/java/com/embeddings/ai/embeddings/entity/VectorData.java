package com.embeddings.ai.embeddings.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@Builder
@ToString
public class VectorData {

        private String name;
        private List<Double> values;
        private Map<String, String> metadata;

}
