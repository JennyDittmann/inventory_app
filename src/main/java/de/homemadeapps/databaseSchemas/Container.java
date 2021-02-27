package de.homemadeapps.databaseSchemas;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "container")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room location;

    public Container() {
    }

    public Container(final String name, final String description, final Room location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Container container = (Container) other;
        return Objects.equals(name, container.name) && Objects.equals(description, container.description) && Objects.equals(location, container.location);
    }
}
