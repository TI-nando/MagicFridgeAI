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

    //Get - Buscar por ID
    @GetMapping("/{id}") // A URL será /food/1, /food/2, etc...
    public ResponseEntity<FoodItem> buscar(@PathVariable Long id) { // @PathVariable pega o "{id}" da URL e coloca na variável "id"
        FoodItem item = service.buscarPorId(id);
        return ResponseEntity.ok(item);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> atualizar(@PathVariable Long id, @RequestBody FoodItem fooditem) {
        FoodItem atualizado = service.atualizar(id, fooditem);
        return ResponseEntity.ok(atualizado);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}