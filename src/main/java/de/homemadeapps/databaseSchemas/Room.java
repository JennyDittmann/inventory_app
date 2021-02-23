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
    @Column(name = "container_id")
    private String container;
}
