package de.homemadeapps.databaseSchemas;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Container> container;

    public Room() {
    }

    public Room(final String name) {
        this(name, new HashSet<>());
    }

    public Room(final String name, final Set<Container> container) {
        this.name = name;
        this.container = container;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name) && Objects.equals(container, room.container);
    }
}
