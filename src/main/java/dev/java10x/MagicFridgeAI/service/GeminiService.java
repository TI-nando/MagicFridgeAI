package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe() {
        String prompt = "Me sugira uma receita simples com ingredientes comuns.";

        // {"text": "Me sugira..."}
        Map<String, Object> part = Map.of("text", prompt);

        // {"parts": [{"text": "..."}]}
        Map<String, Object> content = Map.of("parts", List.of(part));

        // {"contents": [{"parts": [{"text": "..."}]}]}
        Map<String, Object> requestBody = Map.of("contents", List.of(content));

        return webClient.post()
                .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent")
                // Colocando a nossa chave secreta no cabeçalho, como manda o manual
                .header("x-goog-api-key", apiKey)
                .header("Content-Type", "application/json")
                // Inserindo o corpo do texto que montamos acima
                .bodyValue(requestBody)
                // Dispara!
                .retrieve()
                // Pega a resposta e transforma em uma String assíncrona (Mono)
                .bodyToMono(String.class);
    }
}
