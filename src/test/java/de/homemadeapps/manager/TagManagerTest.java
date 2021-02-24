package de.homemadeapps.manager;

import de.homemadeapps.databaseSchemas.Tag;
import de.homemadeapps.repositories.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TagManagerTest {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagManager tagManager;

    @Before
    public void setUp() {
        List<Tag> mockedData = new ArrayList<>(Arrays.asList(new Tag(1, "Krieg der Sterne", "This is really cool Star" +
                " Wars stuff!"), new Tag(2, "Star", "Stuff"), new Tag(3, "Dinge", "Awesome Stuff yey")));
        tagRepository.saveAll(mockedData);
    }

    @Test
    public void saveTag_NoDuplicateExist_success() {

    }

    @Test
    public void saveTag_DuplicateExist_fail() {

    }

    @Test
    public void searchTagsByNameAndDescription_OnHavingData_ReturnList() {
        List<Tag> expectedData = new ArrayList<>(Arrays.asList(new Tag(1, "Krieg der Sterne", "This is really cool Star" +
                " Wars stuff!"), new Tag(2, "Star", "Stuff")));

        List<Tag> result = tagManager.searchTagsByNameAndDescription("Star");

        assertEquals(expectedData, result);
    }

    @Test
    public void searchTagsByNameAndDescription_IfNotExist_ReturnEmptyList() {
        List<Tag> expectedData = new ArrayList<>();

        List<Tag> result = tagManager.searchTagsByNameAndDescription("test");

        assertEquals(expectedData, result);
    }

}
