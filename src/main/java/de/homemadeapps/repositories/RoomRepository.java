package de.homemadeapps.repositories;

import de.homemadeapps.databaseSchemas.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.name LIKE %:name%")
    List<Room> findRoomsByName(String name);
}
