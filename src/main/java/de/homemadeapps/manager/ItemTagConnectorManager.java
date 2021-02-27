package de.homemadeapps.manager;

import de.homemadeapps.databaseSchemas.Item;
import de.homemadeapps.databaseSchemas.ItemTagConnector;
import de.homemadeapps.databaseSchemas.Tag;
import de.homemadeapps.repositories.ItemTagConnectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemTagConnectorManager {
    private final ItemTagConnectorRepository itemTagConnectorRepository;
    private final ItemManager itemManager;
    private final TagManager tagManager;

    @Autowired
    public ItemTagConnectorManager(final ItemTagConnectorRepository itemTagConnectorRepository, ItemManager itemManager,
                                   final TagManager tagManager) {
        this.itemTagConnectorRepository = itemTagConnectorRepository;
        this.itemManager = itemManager;
        this.tagManager = tagManager;
    }

    public List<Item> findItemsByTagName(final String tagName) {
        final List<Tag> tagList = tagManager.searchTagsByName(tagName);
        return getItemsByTags(tagList);
    }

    public List<Item> findItemsByTagDescription(final String tagDescription) {
        final List<Tag> tagList = tagManager.searchTagsByDescription(tagDescription);
        return getItemsByTags(tagList);
    }

    private List<Item> getItemsByTags(final List<Tag> tagList) {
        final List<ItemTagConnector> connections = new ArrayList<>();
        final List<Item> foundItems = new ArrayList<>();
        for (final Tag tag : tagList) {
            connections.addAll(itemTagConnectorRepository.findItemsByTagId(tag.getId()));
        }

        for (final ItemTagConnector connection : connections) {
            foundItems.add(itemManager.getItemById(connection.getItemId()).get());
        }

        return foundItems.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<Item> searchItemsByTags(String searchQuery) {
        final List<Item> descriptionSearchResult = findItemsByTagDescription(searchQuery);
        final List<Item> nameSearchResult = findItemsByTagName(searchQuery);

        Set<Item> result = new LinkedHashSet<>(descriptionSearchResult);
        result.addAll(nameSearchResult);

        return new ArrayList<>(result);
    }
}
