package de.homemadeapps.manager;

import de.homemadeapps.databaseSchemas.Tag;
import de.homemadeapps.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        if (tagRepository.findTagByExactName(name) == null) {
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

    public List<Tag> searchTagsByName(final String query) {
        return tagRepository.findTagsByName(query);
    }

    public List<Tag> searchTagsByDescription(final String query) {
        return tagRepository.findTagsByDescription(query);
    }

    public List<Tag> searchTagsByNameAndDescription(final String query) {
        List<Tag> resultList = new ArrayList<>(tagRepository.findTagsByName(query));
        List<Tag> resultListByDescription = new ArrayList<>(tagRepository.findTagsByDescription(query));

        for (Tag tag : resultListByDescription) {
            if (!resultList.contains(tag)) {
                resultList.add(tag);
            }
        }

        return resultList;
    }


    public boolean deleteTag(final int id) {
        Optional<Tag> tagFromDatabase = tagRepository.findById(id);
        boolean tagIsPresent = tagFromDatabase.isPresent();

        if (tagIsPresent) {
            tagRepository.delete(tagFromDatabase.get());
        }

        return tagIsPresent;
    }
}
