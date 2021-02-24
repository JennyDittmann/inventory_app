package de.homemadeapps.databaseSchemas;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "container")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;

    public Container() {
    }

    public Container(final int id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
