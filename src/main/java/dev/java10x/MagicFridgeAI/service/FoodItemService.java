package dev.java10x.MagicFridgeAI.service;
import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public List<FoodItem> listarTodos() {
        return repository.findAll();
    }

    public Optional<FoodItem> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public FoodItem atualizar(FoodItem fooditem) {
        return repository.save(fooditem);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
