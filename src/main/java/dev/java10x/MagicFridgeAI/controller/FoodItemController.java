package dev.java10x.MagicFridgeAI.controller;
import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }

    // ----- Pontos de Acesso ou Endpoints -----

    //Post
    @PostMapping
    public ResponseEntity<FoodItem> criar(@RequestBody FoodItem fooditem) {
        FoodItem salvo = service.salvar(fooditem);
        return ResponseEntity.ok(salvo);
    }

    //Get - Listar todos
    @GetMapping
    public ResponseEntity<List<FoodItem>> listarTodos() {
        List<FoodItem> listar = service.listar();
        return ResponseEntity.ok(listar); // Retorna status 200 ok
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> atualizar(@PathVariable Long id, @RequestBody FoodItem fooditem) {
        return service.buscarPorId(id)
                .map(itemExistente -> {
                   fooditem.setId(itemExistente.getId());
                   FoodItem atualizado = service.atualizar(fooditem);
                   return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}