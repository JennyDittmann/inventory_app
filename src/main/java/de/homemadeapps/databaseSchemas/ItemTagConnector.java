package de.homemadeapps.databaseSchemas;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item_tag_connector")
public class ItemTagConnector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "tag_id")
    private int tagId;

    public ItemTagConnector() {

    }

    public ItemTagConnector(final int id, final int itemId, final int tagId) {
        this.id = id;
        this.itemId = itemId;
        this.tagId = tagId;
    }
}
