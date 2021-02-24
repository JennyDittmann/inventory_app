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

    public boolean saveTag(final String name, final String description) {
        boolean success = false;
        final Tag tag = new Tag();
        tag.setName(name);
        tag.setDescription(description);

        if (tagRepository.findTagByName(name) == null) {
            tagRepository.save(tag);
            success = true;
        }

        return success;
    }

    public boolean updateTag(final int id, final String name, final String description) {
        boolean success = false;

        if (tagRepository.findById(id).isPresent()) {
            final Tag tag = new Tag(name, description);
            tagRepository.save(tag);
            success = true;
        }

        return success;
    }

    public List<Tag> searchTagsByNameAndDescription(String query) {
        List<Tag> resultList = new ArrayList<>();

        resultList.addAll(tagRepository.findTagsByName(query));
        resultList.addAll(tagRepository.findTagsByDescription(query));

        return resultList;
    }
}
