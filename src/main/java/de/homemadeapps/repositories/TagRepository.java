package de.homemadeapps.repositories;

import de.homemadeapps.databaseSchemas.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
    @Query("SELECT t FROM Tag t WHERE t.name LIKE %:name%")
    List<Tag> findTagsByName(String name);

    @Query("SELECT t FROM Tag t WHERE t.description LIKE %:description%")
    List<Tag> findTagsByDescription(String description);

    @Query("SELECT t FROM Tag t WHERE t.name = :name")
    Tag findTagByExactName(String name);
}
