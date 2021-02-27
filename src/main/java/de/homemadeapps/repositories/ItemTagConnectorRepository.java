package de.homemadeapps.repositories;

import de.homemadeapps.databaseSchemas.ItemTagConnector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemTagConnectorRepository extends CrudRepository<ItemTagConnector, Integer> {

    @Query("SELECT itc FROM ItemTagConnector itc WHERE itc.tagId = :tagId")
    List<ItemTagConnector> findItemsByTagId(int tagId);

    @Query("SELECT itc FROM ItemTagConnector itc WHERE itc.itemId = :itemId")
    List<ItemTagConnector> findTagsByItemId(int itemId);

    @Query("SELECT itc FROM ItemTagConnector itc")
    List<ItemTagConnector> printTable();
}
