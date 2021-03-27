package de.homemadeapps.repositories;

import de.homemadeapps.databaseSchemas.Item;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Item SET name=:name, description=:description WHERE id = :id")
    void update(int id, String name, String description);

    @Query("SELECT i FROM Item i WHERE i.description LIKE %:description%")
    List<Item> findItemsByDescription(String description);

    @Query("SELECT i FROM Item i WHERE i.name LIKE %:name%")
    List<Item> findItemsByName(String name);
}
