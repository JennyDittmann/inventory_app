package de.homemadeapps.repositories;

import de.homemadeapps.databaseSchemas.ItemTagConnector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ItemTagConnectorRepositoryTest {
    @Autowired
    ItemTagConnectorRepository itemTagConnectorRepository;

    @Before
    public void setUp() {
        List<ItemTagConnector> mockedData = Arrays.asList(
                new ItemTagConnector(20, 10),
                new ItemTagConnector(20, 1),
                new ItemTagConnector(2, 10),
                new ItemTagConnector(40, 10));
        itemTagConnectorRepository.saveAll(mockedData);
    }

    @Test
    public void findItemsByTagId_OnHavingData_ReturnItemIds() {
        List<ItemTagConnector> expectedData = Arrays.asList(
                new ItemTagConnector(20, 10),
                new ItemTagConnector(2, 10),
                new ItemTagConnector(40, 10));

        List<ItemTagConnector> result = itemTagConnectorRepository.findItemsByTagId(10);

        assertTrue(expectedData.containsAll(result));
        assertTrue(result.containsAll(expectedData));
    }

    @Test
    public void findItemsByTagId_IfNotExist_ReturnEmptyList() {
        List<ItemTagConnector> expectedData = new ArrayList<>();

        List<ItemTagConnector> result = itemTagConnectorRepository.findItemsByTagId(99);

        assertEquals(expectedData, result);
    }

    @Test
    public void findTagsByItemId_OnHavingData_ReturnTagIds() {
        List<ItemTagConnector> expectedData = Arrays.asList(
                new ItemTagConnector(20, 10),
                new ItemTagConnector(20, 1));

        List<ItemTagConnector> result = itemTagConnectorRepository.findTagsByItemId(20);


        assertTrue(result.containsAll(expectedData));
        assertTrue(expectedData.containsAll(result));

    }

    @Test
    public void findTagsByItemId_IfNOtExist_ReturnEmptyList() {
        List<ItemTagConnector> expectedData = new ArrayList<>();

        List<ItemTagConnector> result = itemTagConnectorRepository.findTagsByItemId(4);

        assertEquals(expectedData, result);
    }
}
