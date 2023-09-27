package com.embeddings.ai.embeddings.service;
import com.embeddings.ai.embeddings.entity.ChatGPTQuery;
import com.embeddings.ai.embeddings.subModules.aiSystem.controller.AIController;
import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTResponse;
import com.embeddings.ai.embeddings.subModules.aiSystem.models.PromptType;
import com.embeddings.ai.embeddings.subModules.aiSystem.service.AIService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;

@Service

public class ChatBotService {

    private final HttpURLConnection connection;

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.url}")
    private String apiUrl;

    public ChatBotService(HttpURLConnection connection){
        this.connection=connection;
    }

    private static final String PINECONE_SEARCH_URL = "http://localhost:5000/search"; // Replace with your actual endpoint

    public String performChatBotAction(ChatGPTQuery query) {
        try {
            PromptType promptType;
            var response=restTemplate.postForObject(apiUrl, query, ChatGPTResponse.class);

            assert response != null;
            String promptWithPineconeResults = PromptType.ONE+ response.toString() + "\n";

            // Call the OpenAI GPT-3 API with the updated prompt and get the response

            return callOpenAI(promptWithPineconeResults);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    private String callOpenAI(String prompt) {


        return ""; // Replace with actual logic
    }

    public void saveEmbeddingsToPinecone(){

    }
}
