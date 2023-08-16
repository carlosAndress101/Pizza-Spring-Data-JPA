package dev.hinestroza.pizzeria.persistence.repository;

import dev.hinestroza.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;


public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {

    /*Nos permite ver las pizzas disponibles y ordenarlas ya sea
    * por precio o name etc
    * */
    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
}
