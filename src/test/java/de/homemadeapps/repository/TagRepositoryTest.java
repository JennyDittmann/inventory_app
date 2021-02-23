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
        List<Tag> mockedData = new ArrayList<>(Arrays.asList(new Tag(1, "Star Wars", "This is really cool Star Wars " +
                "stuff!"), new Tag(2, "Star", "Stuff"), new Tag(3, "Dinge", "Awesome")));
        tagRepository.saveAll(mockedData);
    }

    @Test
    public void findTagsByName_OnHavingData_ReturnList() {
        List<Tag> result = tagRepository.findTagsByName("Star");
        List<Tag> expectedData = new ArrayList<>(Arrays.asList(new Tag(1, "Star Wars", "This is really cool Star Wars" +
                " stuff!"), new Tag(2, "Star", "Stuff")));

        assertEquals(expectedData, result);
    }


    @Test
    public void getTagById_GivenExistingId_ReturnTag() {
        int id = 1;
        final Tag expectedTag = new Tag();
        expectedTag.setId(id);
        expectedTag.setName("Star Wars");
        expectedTag.setDescription("This is really cool Star Wars stuff!");

        Tag result = tagRepository.getTagById(id);

        assertEquals(expectedTag, result);
    }

    @Test
    public void getTagById_IfNotExist_ReturnNull() {
        int id = 99;

        Tag result = tagRepository.getTagById(id);

        assertNull(result);
    }
}
