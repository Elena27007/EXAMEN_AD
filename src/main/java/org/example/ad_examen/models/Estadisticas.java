package org.example.ad_examen.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Estadisticas {
    private long itemsTotales;
    private List<Item> itemsMejorValuados;
}
