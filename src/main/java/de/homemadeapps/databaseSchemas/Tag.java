package de.homemadeapps.databaseSchemas;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tag")
public class Tag {
    public Tag(){}
    public Tag(final String name, final String description){
        this.name = name;
        this.description = description;
    }
    public Tag(final int id, final String name, final String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
}
