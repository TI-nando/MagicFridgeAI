package dev.java10x.MagicFridgeAI.service;
import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    // ---- Inicar os construtores -----

    public FoodItem salvar(FoodItem fooditem) {
        return repository.save(fooditem);
    }

    public List<FoodItem> listar() {
        return repository.findAll();
    }
}
