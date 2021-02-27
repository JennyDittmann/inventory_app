package de.homemadeapps.manager;

import de.homemadeapps.databaseSchemas.Item;
import de.homemadeapps.databaseSchemas.ItemTagConnector;
import de.homemadeapps.databaseSchemas.Tag;
import de.homemadeapps.repositories.ItemRepository;
import de.homemadeapps.repositories.ItemTagConnectorRepository;
import de.homemadeapps.repositories.TagRepository;
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
public class ItemTagConnectorManagerTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ItemTagConnectorRepository itemTagConnectorRepository;
    private ItemTagConnectorManager itemTagConnectorManager;

    @Before
    public void setUp() {
        final ItemManager itemManager = new ItemManager(itemRepository);
        final TagManager tagManager = new TagManager(tagRepository);
        itemTagConnectorManager = new ItemTagConnectorManager(itemTagConnectorRepository, itemManager, tagManager);
        List<Item> mockedItems = Arrays.asList(
                new Item("Comic 47 - Die Rache des Vader", ""),
                new Item("Lego Sternenzerstörer", "Cooles nicht zusammengebautes Lego :)"),
                new Item("Manga", "Band 123465"),
                new Item("Unused :)", "Totally useless..."),
                new Item("Apollo13", "Nen großer Schritt für die Menschheit halt."));
        itemRepository.saveAll(mockedItems);
        List<Tag> mockedTags = Arrays.asList(
                new Tag("Star Wars", "This is really cool Star Wars stuff!"),
                new Tag("Buch", "Bücher, Romane, Mangas"),
                new Tag("Lego", "Lego oder andere Klemmbausteine"),
                new Tag("Nichts", "Ne wirklich nichts"),
                new Tag("Weltraum", "Nicht alles ausm Weltraum ist Star Wars"));
        tagRepository.saveAll(mockedTags);
        List<ItemTagConnector> mockedConnector = Arrays.asList(
                new ItemTagConnector(mockedItems.get(0).getId(), mockedTags.get(0).getId()),
                new ItemTagConnector(mockedItems.get(1).getId(), mockedTags.get(2).getId()),
                new ItemTagConnector(mockedItems.get(1).getId(), mockedTags.get(0).getId()),
                new ItemTagConnector(mockedItems.get(0).getId(), mockedTags.get(1).getId()),
                new ItemTagConnector(mockedItems.get(2).getId(), mockedTags.get(1).getId()),
                new ItemTagConnector(mockedItems.get(1).getId(), mockedTags.get(4).getId()),
                new ItemTagConnector(mockedItems.get(4).getId(), mockedTags.get(4).getId()));
        itemTagConnectorRepository.saveAll(mockedConnector);
    }

    @Test
    public void findItemsByTagName_OnHavingData_ReturnItemList() {
        List<Item> expectedData = Arrays.asList(
                new Item("Lego Sternenzerstörer", "Cooles nicht zusammengebautes Lego :)"),
                new Item("Comic 47 - Die Rache des Vader", ""));

        List<Item> result = itemTagConnectorManager.findItemsByTagName("Star");

        assertTrue(expectedData.containsAll(result));
        assertTrue(result.containsAll(expectedData));
    }

    @Test
    public void findItemsByTagName_IfNotExist_ReturnEmptyList() {
        List<Item> expectedData = new ArrayList<>();

        List<Item> result = itemTagConnectorManager.findItemsByTagName("Festival");

        assertEquals(expectedData, result);
    }

    @Test
    public void findItemsByTagDescription_OnHavingData_ReturnItemList() {
        List<Item> expectedData = Arrays.asList(
                new Item("Comic 47 - Die Rache des Vader", ""),
                new Item("Manga", "Band 123465"));

        List<Item> result = itemTagConnectorManager.findItemsByTagDescription("Bücher");

        assertEquals(expectedData, result);
    }

    @Test
    public void findItemsByTagDescription_IfNotExist_ReturnEmptyList() {
        List<Item> expectedData = new ArrayList<>();

        List<Item> result = itemTagConnectorManager.findItemsByTagDescription("Festivals, Camping, Sommer");

        assertEquals(expectedData, result);
    }

    @Test
    public void searchItemsByTags_OnHavingDataWithoutStrategy_ReturnItemList() {
        List<Item> expectedData = Arrays.asList(
                new Item("Lego Sternenzerstörer", "Cooles nicht zusammengebautes Lego :)"),
                new Item("Comic 47 - Die Rache des Vader", ""),
                new Item("Apollo13", "Nen großer Schritt für die Menschheit halt."));

        List<Item> result = itemTagConnectorManager.searchItemsByTags("Star Wars");

//        assertTrue(expectedData.containsAll(result));
        assertTrue(result.containsAll(expectedData));
    }

    @Test
    public void searchItemsByTags_IfItemNotExist_ReturnEmptyList() {
        List<Item> expectedData = new ArrayList<>();

        List<Item> result = itemTagConnectorManager.searchItemsByTags("Nichts");

        assertEquals(expectedData, result);
    }

    @Test
    public void searchItemsByTags_IfTagNotExist_ReturnEmptyList() {
        List<Item> expectedData = new ArrayList<>();

        List<Item> result = itemTagConnectorManager.searchItemsByTags("Festival");

        assertEquals(expectedData, result);

    }

//    @Test
//    public void searchItemsByTags_OnHavingDataWithPreferTagNameStrategy_ReturnItemList() {
//        List<Item> expectedData = Arrays.asList(
//                new Item("Lego Sternenzerstörer", "Cooles nicht zusammengebautes Lego :)"),
//                new Item("Comic 47 - Die Rache des Vader", ""),
//                new Item("Apollo13", "Nen großer Schritt für die Menschheit halt."));
//
//        List<Item> result = itemTagConnectorManager.searchItemsByTags("Star Wars");
//
//        assertEquals(expectedData, result);
//    }

}
