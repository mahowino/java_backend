package com.embeddings.ai.embeddings.subModules.aiSystem.exceptions;

public class AICommunicationException extends RuntimeException{
    public AICommunicationException() {
        super("There was an error communicating with GPT");
    }
}
