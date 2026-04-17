package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeminiService {

    private final WebClient webClient;
    private String apiKey =  "";
    public GeminiService(WebClient webClient) {}


    public void generateRecipe() {
    }
}
