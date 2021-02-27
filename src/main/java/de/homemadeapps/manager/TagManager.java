package de.homemadeapps.manager;

import de.homemadeapps.databaseSchemas.Tag;
import de.homemadeapps.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    public List<Tag> searchTagsByNameAndDescription(String query) {
        List<Tag> resultList = new ArrayList<>(tagRepository.findTagsByName(query));
        List<Tag> resultListByDescription = new ArrayList<>(tagRepository.findTagsByDescription(query));

        for (Tag tag : resultListByDescription) {
            if (!resultList.contains(tag)) {
                resultList.add(tag);
            }
        }

        return resultList;
    }


    public boolean deleteTag(Tag tag) {
        boolean success = false;
        Optional<Tag> tagFromDatabase = tagRepository.findById(tag.getId());
        boolean tagIsPresent = tagFromDatabase.isPresent();

        if(tagIsPresent && tagFromDatabase.get().equals(tag)){
            tagRepository.delete(tag);
            success = true;
        }

        return success;
    }
}
