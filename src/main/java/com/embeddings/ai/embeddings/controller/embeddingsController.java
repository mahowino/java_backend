package com.embeddings.ai.embeddings.controller;

import com.embeddings.ai.embeddings.entity.VectorData;
import com.embeddings.ai.embeddings.subModules.embeddingsSystem.embeddingsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class embeddingsController {
 embeddingsService service=new embeddingsService();

 @GetMapping("/")
    public void getEmbeddings(){
         service.getEmbeddingsFromText();
    }
}
