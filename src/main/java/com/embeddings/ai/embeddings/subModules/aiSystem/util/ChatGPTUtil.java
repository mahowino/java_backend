package com.embeddings.ai.embeddings.subModules.aiSystem.util;


import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTRequest;
import com.embeddings.ai.embeddings.subModules.aiSystem.models.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;

public class ChatGPTUtil {
    private final HttpURLConnection connection;

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.url}")
    private String apiUrl;

    public ChatGPTUtil(HttpURLConnection connection){
        this.connection=connection;
    }

    public ChatGPTResponse readResponse(ChatGPTRequest chatGPTRequest) throws Exception {

        //todo: add error handling
        ChatGPTResponse response=restTemplate.postForObject(apiUrl, chatGPTRequest, ChatGPTResponse.class);
        assert response != null;
        System.out.println(response);

        // return the first response
        return response;

    }


}
