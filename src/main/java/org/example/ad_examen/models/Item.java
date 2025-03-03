package org.example.ad_examen.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Clase que representa un item.
 */
@Document(collection = "API_ITEMS")
@Data
public class Item {
    @Id
    @Field("_id")
    private ObjectId id;
    private int idItem;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private double rate;
    private int count;

}
