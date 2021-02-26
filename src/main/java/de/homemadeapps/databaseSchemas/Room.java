package de.homemadeapps.databaseSchemas;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name= "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Container> container;

    public Room(){}

    public Room(final int id, final String name){
        this(id,name, new HashSet<>());
    }
    public Room(final int id, final String name, final Set<Container> container){
        this.id = id;
        this.name = name;
        this.container = container;
    }
}
