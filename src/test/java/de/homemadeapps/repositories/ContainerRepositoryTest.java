package de.homemadeapps.repositories;

import de.homemadeapps.databaseSchemas.Container;
import de.homemadeapps.databaseSchemas.Room;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles
public class ContainerRepositoryTest {
    @Autowired
    private ContainerRepository containerRepository;
    @Autowired
    private RoomRepository roomRepository;

    private Room bedRoom;
    private Room livingRoom;

    @Before
    public void setUp() {
        bedRoom = new Room("Schlafzimmer");
        livingRoom = new Room("Wohnzimmer");
        roomRepository.saveAll(Arrays.asList(bedRoom, livingRoom));

        List<Container> mockedData = Arrays.asList(
                new Container("Schrank", "großer Kleiderschrank", bedRoom),
                new Container("Karton", "Aus Pappe :)", livingRoom));
        containerRepository.saveAll(mockedData);
    }

    @Test
    public void findContainersByName_OnHavingData_ReturnList() {
        List<Container> expectedData = Collections.singletonList(
                new Container("Karton", "Aus Pappe :)", livingRoom));

        List<Container> result = containerRepository.findContainersByName("Karton");

        assertEquals(expectedData, result);
    }

    @Test
    public void findContainersByName_IfNotExist_ReturnEmptyList() {
        List<Container> expectedData = new ArrayList<>();

        List<Container> result = containerRepository.findContainersByName("Schatulle");

        assertEquals(expectedData, result);
    }

    @Test
    public void findContainersByDescription_OnHavingData_ReturnList() {
        List<Container> expectedData = Collections.singletonList(
                new Container("Schrank", "großer Kleiderschrank", bedRoom));

        List<Container> result = containerRepository.findContainersByDescription("Kleiderschrank");

        assertEquals(expectedData, result);
    }

    @Test
    public void findContainersByDescription_IfNotExist_ReturnEmptyList() {
        List<Container> expectedData = new ArrayList<>();

        List<Container> result = containerRepository.findContainersByDescription("goldene Piratenkiste");

        assertEquals(expectedData, result);
    }
}
