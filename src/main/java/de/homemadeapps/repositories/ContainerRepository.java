package de.homemadeapps.repositories;

import de.homemadeapps.databaseSchemas.Container;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRepository extends CrudRepository<Container, Integer> {

    @Query("SELECT c FROM Container c WHERE c.name LIKE %:name%")
    List<Container> findContainersByName(String name);

    @Query("SELECT c FROM Container c WHERE c.description LIKE %:description%")
    List<Container> findContainersByDescription(String description);
}
