package de.homemadeapps.manager;

import de.homemadeapps.databaseSchemas.Tag;
import de.homemadeapps.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagManager {
    private final TagRepository tagRepository;

    @Autowired
    public TagManager(final TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> searchTagsByNameAndDescription(String query) {
        List<Tag> test = new ArrayList<>();
        return test;
    }
}
