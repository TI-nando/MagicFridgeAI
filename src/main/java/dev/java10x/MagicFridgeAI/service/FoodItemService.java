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

    public FoodItem buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    public FoodItem atualizar(Long id, FoodItem dadosAtualizados) {
        // 1. Busca o item antigo no banco
        FoodItem itemAntigo = buscarPorId(id);

        // 2. Atualizamos os atribustos (não atualiza o ID!)
        itemAntigo.setName(dadosAtualizados.getName());
        itemAntigo.setCategoria(dadosAtualizados.getCategoria());
        itemAntigo.setQuantidade(dadosAtualizados.getQuantidade());
        itemAntigo.setValidade(dadosAtualizados.getValidade());

        // 3. Salvamos por cima
        return repository.save(itemAntigo);
    }

    public void deletar(Long id) {
        // Sera verificado se existe antes de tentar deletar
        FoodItem item = buscarPorId(id);
        repository.delete(item);
    }
}
