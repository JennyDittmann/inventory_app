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
        List<Item> mockedItems = Arrays.asList(new Item(1, "Lego Sternenzerstörer", "Cooles nicht zusammengebautes " +
                        "Lego :)"), new Item(2, "Comic 47 - Die Rache des Vader", ""), new Item(3, "Manga", "Band 123465"),
                new Item(4, "Unused :)", "Totally useless..."));
        itemRepository.saveAll(mockedItems);
        List<Tag> mockedTags = Arrays.asList(new Tag(1, "Star Wars", "This is really cool Star Wars " +
                "stuff!"), new Tag(2, "Buch", "Bücher, Romane, Mangas"), new Tag(3, "Lego", "Lego oder andere " +
                "Klemmbausteine"), new Tag(4, "Nichts", "Ne wirklich nichts"));
        tagRepository.saveAll(mockedTags);
        List<ItemTagConnector> mockedConnector = Arrays.asList(new ItemTagConnector(1, 1, 1), new ItemTagConnector(2, 1,
                3), new ItemTagConnector(3, 2, 1), new ItemTagConnector(4, 2, 2), new ItemTagConnector(4, 3, 2));
        itemTagConnectorRepository.saveAll(mockedConnector);
    }

    @Test
    public void findItemsByTagName_OnHavingData_ReturnItemList() {
        List<Item> expectedData = Arrays.asList(new Item(1, "Lego Sternenzerstörer", "Cooles nicht zusammengebautes " +
                "Lego :)"), new Item(2, "Comic 47 - Die Rache des Vader", ""));

        List<Item> result = itemTagConnectorManager.findItemsByTagName("Star");

        assertEquals(expectedData, result);
    }

    @Test
    public void findItemsByTagName_IfNotExist_ReturnEmptyList() {
        List<Item> expectedData = new ArrayList<>();

        List<Item> result = itemTagConnectorManager.findItemsByTagName("Festival");

        assertEquals(expectedData, result);
    }

    @Test
    public void findItemsByTagDescription_OnHavingData_ReturnItemList(){
        List<Item> expectedData = Arrays.asList(new Item(2, "Comic 47 - Die Rache des Vader", ""), new Item(3, "Manga", "Band 123465"));

        List<Item> result = itemTagConnectorManager.findItemsByTagDescription("Bücher");

        assertEquals(expectedData,result);
    }

    @Test
    public void findItemsByTagDescription_IfNotExist_ReturnEmptyList(){
        List<Item> expectedData = new ArrayList<>();

        List<Item> result = itemTagConnectorManager.findItemsByTagDescription("Festivals, Camping, Sommer");

        assertEquals(expectedData,result);
    }
}
