package dev.java10x.MagicFridgeAI.service;
import dev.java10x.MagicFridgeAI.model.FoodItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<String> generateRecipe(List<FoodItem> fooditems  ) {

        String alimentos = fooditems.stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, Validade: %s",
                        item.getName(),item.getCategoria(), item.getQuantidade(), item.getValidade()))
                .collect(Collectors.joining("/n"));

        String prompt = "Baseado no meu banco de dados faça uma receita com os seguintes itens: " + alimentos;

        Map<String, Object> part = Map.of("text", prompt);
        Map<String, Object> content = Map.of("parts", List.of(part));
        Map<String, Object> requestBody = Map.of("contents", List.of(content));

        return webClient.post()
                .uri("")
                .header("x-goog-api-key", apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);
    }
}