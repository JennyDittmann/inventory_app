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

    public ItemTagConnector(final int itemId, final int tagId) {
        this.itemId = itemId;
        this.tagId = tagId;
    }

    public boolean equals(final Object other){
        boolean isEquals = false;
        if(other instanceof ItemTagConnector){
            ItemTagConnector connector = (ItemTagConnector) other;
            isEquals = this.tagId == connector.tagId && this.itemId == connector.itemId;
        }
        return isEquals;
    }
}
