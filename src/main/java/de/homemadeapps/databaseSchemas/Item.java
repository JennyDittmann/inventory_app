package de.homemadeapps.databaseSchemas;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;

    public Item() {
    }

    public Item(final int id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
