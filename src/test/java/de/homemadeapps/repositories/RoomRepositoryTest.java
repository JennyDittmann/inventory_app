package de.homemadeapps.repositories;

import de.homemadeapps.databaseSchemas.Item;
import de.homemadeapps.databaseSchemas.Room;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RoomRepositoryTest {
    @Autowired
    private RoomRepository roomRepository;

    @Before
    public void setUp(){
       List<Room> mockedData = Arrays.asList(
               new Room("Gaming-Zimmer"),
               new Room("Wohnzimmer"));
       roomRepository.saveAll(mockedData);
    }

    @Test
    public void findRoomByName_OnHavingData_ReturnList() {
        List<Room> expectedData = Collections.singletonList(new Room( "Gaming-Zimmer"));

        List<Room> result = roomRepository.findRoomsByName("Zimmer");

        assertEquals(expectedData, result);
    }

    @Test
    public void findRoomByName_IfNotExist_ReturnEmptyList() {
        List<Room> expectedData = new ArrayList<>();

        List<Room> result = roomRepository.findRoomsByName("Thronsaal");

        assertEquals(expectedData, result);
    }
}
