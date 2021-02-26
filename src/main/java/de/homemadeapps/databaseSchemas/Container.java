package de.homemadeapps.databaseSchemas;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "container")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room location;

    public Container() {
    }

    public Container(final int id, final String name, final String description,
                     final Room location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
    }
}
