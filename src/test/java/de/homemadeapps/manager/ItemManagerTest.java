package de.homemadeapps.manager;

import de.homemadeapps.databaseSchemas.Item;
import de.homemadeapps.repositories.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ItemManagerTest {
    @Autowired
    private ItemRepository itemRepository;
    private ItemManager itemManager;

    @Before
    public void setUp() {
        itemManager = new ItemManager(itemRepository);
        List<Item> mockedData = Arrays.asList(
                new Item(1, "Dragonball Band 1", "Band 1 des Mangas Dragonball - Deutsch"),
                new Item(2, "Wallace", "Teddybär, Grün mit rotem Schaal"),
                new Item(3, "Manga", "Band 123465"));
        itemRepository.saveAll(mockedData);
    }

    @Test
    public void saveItem_IfNoDuplicateExist_ReturnTrue() {
        boolean result = itemManager.saveItem("19x19 Goban", "Brett zum Spielen von Go");

        assertTrue(result);
    }

    @Test
    public void saveItem_IfDuplicateExist_ReturnFalse() {
        boolean result = itemManager.saveItem("Wallace", "Teddybär, Grün mit rotem Schaal");

        assertFalse(result);
    }

    @Test
    public void updateItem_IfItemIsPresent_ReturnTrue() {
        boolean result = itemManager.updateItem(1, "Dragonball Band 1", "Lol :)");

        assertTrue(result);
    }

    @Test
    public void updateItem_IfItemIsNotPresent_ReturnFalse() {
        boolean result = itemManager.updateItem(15, "Dinge", "Dinge Dinge");

        assertFalse(result);
    }

    @Test
    public void searchItemsByNameAndDescription_OnHavingData_ReturnList() {
        List<Item> expectedData = Arrays.asList(
                new Item(1, "Dragonball Band 1", "Band 1 des Mangas Dragonball - Deutsch"),
                new Item(3, "Manga", "Band 123465"));

        List<Item> result = itemManager.searchItemsByNameAndDescription("Band");

        assertEquals(expectedData, result);

    }

    @Test
    public void deleteItem_IfExist_ReturnTrue() {
        Item item = new Item(2, "Wallace", "Teddybär, Grün mit rotem Schaal");
        boolean result = itemManager.deleteItem(item);

        assertTrue(result);
    }

    @Test
    public void deleteItem_IfNotExist_ReturnFalse() {
        Item item = new Item(99, "Grillzange", "IKEA Grillzange, Grün");
        boolean result = itemManager.deleteItem(item);

        assertFalse(result);
    }

}
