package de.homemadeapps.repositories;

import de.homemadeapps.databaseSchemas.Item;
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
public class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    private List<Item> mockedData;

    @Before
    public void setUp() {
        mockedData = Arrays.asList(
                new Item(1, "Pictures", "Spiel für 3-5 Personen."),
                new Item(2, "Ligretto", "Spiel für 2-4 Personen."));
        itemRepository.saveAll(mockedData);
    }

    @Test
    public void findItemsByName_OnHavingData_ReturnList() {
        List<Item> expectedData = Collections.singletonList(new Item(1, "Pictures", "Spiel für 3-5 Personen."));

        List<Item> result = itemRepository.findItemsByName("Pictures");

        assertEquals(expectedData, result);
    }

    @Test
    public void findItemsByName_IfNotExist_ReturnEmptyList() {
        List<Item> expectedData = new ArrayList<>();

        List<Item> result = itemRepository.findItemsByName("Kniffel");

        assertEquals(expectedData, result);
    }

    @Test
    public void findItemsByDescription_OnHavingData_ReturnList() {
        List<Item> result = itemRepository.findItemsByDescription("Personen");

        assertEquals(mockedData, result);
    }

    @Test
    public void findItemsByDescription_IfNotExist_ReturnEmptyList() {
        List<Item> expectedData = new ArrayList<>();

        List<Item> result = itemRepository.findItemsByDescription("Weihnachten");

        assertEquals(expectedData, result);
    }
}
