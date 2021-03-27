package de.homemadeapps.repositories;

import de.homemadeapps.databaseSchemas.Tag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Tag SET name=:name, description=:description WHERE id = :id")
    void update(int id, String name, String description);

    @Query("SELECT t FROM Tag t WHERE t.name LIKE %:name%")
    List<Tag> findTagsByName(String name);

    @Query("SELECT t FROM Tag t WHERE t.description LIKE %:description%")
    List<Tag> findTagsByDescription(String description);

    @Query("SELECT t FROM Tag t WHERE t.name = :name")
    Tag findTagByExactName(String name);
}
