package dev.hinestroza.pizzeria.persistence.repository;

import dev.hinestroza.pizzeria.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    /*Query Methods*/
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodIn(List<String> methods);
}
