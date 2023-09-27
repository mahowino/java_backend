package com.embeddings.ai.embeddings.repository;

import com.embeddings.ai.embeddings.entity.PDFChunks;
import com.embeddings.ai.embeddings.entity.TextEmbeddingData;
import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTRequest;
import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

public class EmbeddingsRepository {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    private String apiUrl="https://api.openai.com/v1/embeddings/";
    public TextEmbeddingData readResponse(PDFChunks chunks) throws Exception {

        //todo: add error handling
        TextEmbeddingData response=restTemplate.postForObject(apiUrl, chunks, TextEmbeddingData.class);
        assert response != null;
        System.out.println(response);

        // return the first response
        return response;

    }

}
