package dev.hinestroza.pizzeria.web.controller;

import dev.hinestroza.pizzeria.persistence.entity.PizzaEntity;
import dev.hinestroza.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue ="8") int element) {
        return ResponseEntity.ok(this.pizzaService.getAll(page, element));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int idPizza) {
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    /*Query methods*/
//    @GetMapping("/available")
//    public ResponseEntity<List<PizzaEntity>> getAvailable() {
//        return ResponseEntity.ok(this.pizzaService.getAvailable());
//    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue ="8") int elements,
                                                          @RequestParam(defaultValue ="price") String sortBy,
                                                          @RequestParam(defaultValue ="ASC") String sortDirection) {
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    //nos trae todos los registro con ese nombre.
    @GetMapping("/available/{name}")
    public ResponseEntity<PizzaEntity> getAvailableName(@PathVariable String name) {
        return ResponseEntity.ok(this.pizzaService.getAvailableName(name));
    }

    //nos trae un solo registro por nombre
    @GetMapping("/availablefirst/{name}")
    public ResponseEntity<PizzaEntity> getAvailableNameFirst(@PathVariable String name) {
        return ResponseEntity.ok(this.pizzaService.getAvailableNameFirst(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    @GetMapping("/notWith/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getNotWith(@PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.getNotWith(ingredient));
    }

    @GetMapping("/tresavalaible/{price}")
    public ResponseEntity<List<PizzaEntity>> getTresPizzaThatAvailable(@PathVariable double price) {
        return ResponseEntity.ok(this.pizzaService.getTresPizzaThatAvalailable(price));
    }
    /******************/

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza) {
        if(pizza.getIdPizza() == null || !this.pizzaService.exist(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if(pizza.getIdPizza() != null && this.pizzaService.exist(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza) {
        if(this.pizzaService.exist(idPizza)){
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
