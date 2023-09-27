package com.embeddings.ai.embeddings.subModules.aiSystem.config;



import com.embeddings.ai.embeddings.subModules.aiSystem.mapper.ChatGPTDtoRequestMapper;
import com.embeddings.ai.embeddings.subModules.aiSystem.mapper.ChatGPTDtoResponseMapper;
import com.embeddings.ai.embeddings.subModules.aiSystem.util.ChatGPTUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


@Configuration
public class AIConfig {
    @Value("${openai.api.key}")
    private String apiKey;

    @Bean
    public HttpURLConnection connection() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) getUrl().openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setDoOutput(true);
        return connection;
    }
    @Bean
    @Qualifier("openaiRestTemplate")
    public RestTemplate openaiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + apiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }

    @Bean
    public ChatGPTUtil chatGPTUtil() throws IOException {return new ChatGPTUtil(connection());}

    @Bean
    public ChatGPTDtoRequestMapper chatGPTDtoRequestMapper(){return new ChatGPTDtoRequestMapper();}

    @Bean
    public ChatGPTDtoResponseMapper chatGPTDtoResponseMapper(){return new ChatGPTDtoResponseMapper();}

    private URL getUrl() throws MalformedURLException {
        return new URL("https://api.openai.com/v1/chat/completions");
    }
}
