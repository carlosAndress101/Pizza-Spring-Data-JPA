package dev.hinestroza.pizzeria.service;

import dev.hinestroza.pizzeria.persistence.entity.PizzaEntity;
import dev.hinestroza.pizzeria.persistence.repository.PizzaPagSortRepository;
import dev.hinestroza.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository){
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    //Lista de pizza paginadas
    public Page<PizzaEntity> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public PizzaEntity get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza){
     return this.pizzaRepository.save(pizza);
    }

    public void delete(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

    public boolean exist(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }

    /*Query methods*/
    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

//    public List<PizzaEntity> getAvailable(){
//        //System.out.println(this.pizzaRepository.countByVeganTrue());
//        return this.pizzaRepository.findAllByAvailableTrueOrderByPriceAsc();
//    }

    public PizzaEntity getAvailableName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public PizzaEntity getAvailableNameFirst(String name){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getWith(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }
    public List<PizzaEntity> getNotWith(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getTresPizzaThatAvalailable(double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

//    public int countPizzaVegan(){
//        return this.pizzaRepository.countByVeganTrue();
//    }
}
