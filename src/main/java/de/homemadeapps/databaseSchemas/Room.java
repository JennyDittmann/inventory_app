package de.homemadeapps.databaseSchemas;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public Room(){}

    public Room(final int id, final String name){
        this.id = id;
        this.name = name;
    }
}
