package com.embeddings.ai.embeddings.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PDFChunks {
    String input;
    String model;
}
