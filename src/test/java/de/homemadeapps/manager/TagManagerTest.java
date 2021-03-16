package de.homemadeapps.manager;

import de.homemadeapps.databaseSchemas.Tag;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TagManagerTest {
    @Autowired
    private TagRepository tagRepository;
    private TagManager tagManager;

    @Before
    public void setUp() {
        tagManager = new TagManager(tagRepository);
        List<Tag> mockedData = new ArrayList<>(Arrays.asList(
                new Tag("Krieg der Sterne", "This is really cool Star Wars stuff!"),
                new Tag("Star", "Stuff"),
                new Tag("Dinge", "Awesome Stuff yey")));
        tagRepository.saveAll(mockedData);
    }

    @Test
    public void saveTag_IfNoDuplicateExist_ReturnTrue() {
        boolean result = tagManager.saveTag("test", "zesz");

        assertTrue(result);
    }

    @Test
    public void saveTag_IfDuplicateExist_ReturnFalse() {
        boolean result = tagManager.saveTag("Krieg der Sterne", "test");

        assertFalse(result);
    }

    @Test
    public void updateTag_IfTagExists_ReturnTrue() {
        boolean result = tagManager.updateTag(1, "Star Wars", "New new new!");

        assertTrue(result);
    }

    @Test
    public void updateTag_IfTagDoesNotExist_ReturnFalse() {
        boolean result = tagManager.updateTag(20, "Does not", "Exist...");

        assertFalse(result);
    }

    @Test
    public void searchTagsByNameAndDescription_OnHavingData_ReturnList() {
        List<Tag> expectedData = Arrays.asList(
                new Tag("Krieg der Sterne", "This is really cool Star Wars stuff!"),
                new Tag("Star", "Stuff"));

        List<Tag> result = tagManager.searchTagsByNameAndDescription("Star");

        assertTrue(expectedData.containsAll(result));
        assertTrue(result.containsAll(expectedData));
    }

    @Test
    public void searchTagsByNameAndDescription_IfNotExist_ReturnEmptyList() {
        List<Tag> expectedData = new ArrayList<>();

        List<Tag> result = tagManager.searchTagsByNameAndDescription("test");

        assertEquals(expectedData, result);
    }

    @Test
    public void deleteTag_IfExist_ReturnTrue() {
        boolean result = tagManager.deleteTag(1);

        assertTrue(result);
    }

    @Test
    public void deleteTag_IfNotExist_ReturnFalse() {
        boolean result = tagManager.deleteTag(99);

        assertFalse(result);
    }

}
