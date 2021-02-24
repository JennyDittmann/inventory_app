package de.homemadeapps.repository;

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
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TagRepositoryTest {
    @Autowired
    private TagRepository tagRepository;

    @Before
    public void setUp() {
        List<Tag> mockedData = Arrays.asList(new Tag(1, "Star Wars", "This is really cool Star Wars " +
                "stuff!"), new Tag(2, "Star", "Stuff"), new Tag(3, "Dinge", "Awesome Stuff yey"));
        tagRepository.saveAll(mockedData);
    }

    @Test
    public void findTagsByDescription_OnHavingData_ReturnList() {
        List<Tag> expectedData = Collections.singletonList(new Tag(3, "Dinge", "Awesome Stuff yey"));

        List<Tag> result = tagRepository.findTagsByDescription("Awesome");

        assertEquals(expectedData, result);
    }

    @Test
    public void findTagsByDescription_IfNotExist_ReturnEmptyList() {
        List<Tag> expectedData = new ArrayList<>();

        List<Tag> result = tagRepository.findTagsByDescription("Noep");

        assertEquals(expectedData, result);

    }

    @Test
    public void findTagByExactName_OnHavingData_ReturnTag() {
        Tag expectedData = new Tag(2, "Star", "Stuff");

        Tag result = tagRepository.findTagByExactName("Star");

        assertEquals(expectedData, result);
    }

    @Test
    public void findTagByExactName_IfNotExist_ReturnNull() {
        Tag result = tagRepository.findTagByExactName("Test");

        assertNull(result);
    }

    @Test
    public void findTagsByName_OnHavingData_ReturnList() {
        List<Tag> expectedData = Arrays.asList(new Tag(1, "Star Wars", "This is really cool Star Wars" +
                " stuff!"), new Tag(2, "Star", "Stuff"));

        List<Tag> result = tagRepository.findTagsByName("Star");

        assertEquals(expectedData, result);
    }

    @Test
    public void findTagsByName_IfNotExist_ReturnEmptyList() {
        List<Tag> expected = new ArrayList<>();

        List<Tag> result = tagRepository.findTagsByName("Noep");

        assertEquals(expected, result);
    }
}
