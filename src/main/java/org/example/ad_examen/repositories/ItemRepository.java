package org.example.ad_examen.repositories;

import org.example.ad_examen.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repositorio de items
 */
public interface ItemRepository extends MongoRepository<Item, String> {

    List<Item> findAllByCategory(String category);
    List<Item> findAllByRateGreaterThan(double rate);
}
