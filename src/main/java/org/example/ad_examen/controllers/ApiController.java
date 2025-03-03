package org.example.ad_examen.controllers;

import org.example.ad_examen.models.Estadisticas;
import org.example.ad_examen.models.Item;
import org.example.ad_examen.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador de la API de items
 */
@RestController
@RequestMapping("/items")
public class ApiController {

    /**
     * Repositorio de items
     */
    @Autowired
    ItemRepository itemRepository;

    /**
     * Obtener los detalles de un item por su id
     * @param id ID del item
     * @return Item
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> obtenerDetallesItem(@PathVariable String id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Agregar un nuevo item
     * @param item Datos del item
     * @return item
     */
    @PostMapping
    public Item agregarNuevoItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    /**
     * Eliminar un item por su id
     * @param id ID del item
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarItem(@PathVariable String id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            itemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Listar items por categoría
     * @param category Categoría
     * @return Lista de items
     */
    @GetMapping("/category/{category}")
    public List<Item> listarItemsPorCategoria(@PathVariable String category) {
        return itemRepository.findAllByCategory(category);
    }

    /**
     * Obtener estadísticas de los items
     * @param minRate
     * @return
     */
    @GetMapping("/stats")
    public ResponseEntity<Estadisticas> obtenerEstadisticas(@RequestParam double minRate) {
        long itemsTotales = itemRepository.count();
        List<Item> itemsMejorValuados = itemRepository.findAllByRateGreaterThan(minRate);
        Estadisticas stats = new Estadisticas(itemsTotales, itemsMejorValuados);
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }


}
