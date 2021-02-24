package de.homemadeapps.repositories;

import de.homemadeapps.databaseSchemas.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

    @Query("SELECT i FROM Item i WHERE i.description LIKE %:description%")
    List<Item> findItemsByDescription(String description);

    @Query("SELECT i FROM Item i WHERE i.name LIKE %:name%")
    List<Item> findItemsByName(String name);
}
