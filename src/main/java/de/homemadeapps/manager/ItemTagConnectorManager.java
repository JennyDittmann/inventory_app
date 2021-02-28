package de.homemadeapps.manager;

import de.homemadeapps.EnrichedSearchResult;
import de.homemadeapps.SearchStrategy;
import de.homemadeapps.comparators.PreferTagCountStrategyComparator;
import de.homemadeapps.comparators.SimpleSortStrategyComparator;
import de.homemadeapps.comparators.UnsortedStrategyComparator;
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
            Optional<Item> item = itemManager.getItemById(connection.getItemId());
            item.ifPresent(foundItems::add);
        }

        return foundItems.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<Item> searchItemsByTags(final String searchQuery) {
        return searchItemsByTags(searchQuery, SearchStrategy.UNSORTED);
    }

    public List<Item> searchItemsByTags(final String searchQuery, final SearchStrategy searchStrategy) {
        final List<Item> result = new ArrayList<>();
        final List<Item> descriptionSearchResult = findItemsByTagDescription(searchQuery);
        final List<Item> nameSearchResult = findItemsByTagName(searchQuery);
        List<EnrichedSearchResult<Item>> enrichedSearchResults = createEnrichedTagSearchResults(nameSearchResult,
                descriptionSearchResult);
        Comparator<EnrichedSearchResult<Item>> sortStrategy;

        if (searchStrategy.equals(SearchStrategy.SORTED)) {
            sortStrategy = new SimpleSortStrategyComparator<>();
        } else if (searchStrategy.equals(SearchStrategy.PREFER_TAG_COUNT)) {
            sortStrategy = new PreferTagCountStrategyComparator<>();
        } else {
            sortStrategy = new UnsortedStrategyComparator<>();
        }
        enrichedSearchResults.sort(sortStrategy);
        for(EnrichedSearchResult<Item> enrichedItem : enrichedSearchResults){
            result.add(enrichedItem.result);
        }

        return result;
    }

    //TODO: Find better naming... :)
    private List<EnrichedSearchResult<Item>> createEnrichedTagSearchResults(final List<Item> itemListName,
                                                                           final List<Item> itemListDescription) {
        List<EnrichedSearchResult<Item>> enrichedSearchResults = new ArrayList<>();

        for (final Item item : itemListName) {
            addItemIfAbsent(enrichedSearchResults, item);
            Optional<EnrichedSearchResult<Item>> enrichedResult = getItemEnrichedSearchResult(enrichedSearchResults, item);
            enrichedResult.get().increaseNameCount();
        }
        for (final Item item : itemListDescription) {
            addItemIfAbsent(enrichedSearchResults, item);
            Optional<EnrichedSearchResult<Item>> enrichedResult = getItemEnrichedSearchResult(enrichedSearchResults, item);
            enrichedResult.get().increaseDescriptionCount();
        }

        return enrichedSearchResults;
    }

    private void addItemIfAbsent(List<EnrichedSearchResult<Item>> enrichedSearchResults, Item item) {
        EnrichedSearchResult<Item> enrichedResult;
        Optional<EnrichedSearchResult<Item>> maybeItem = getItemEnrichedSearchResult(enrichedSearchResults, item);

        if (maybeItem.isEmpty()) {
            enrichedResult = new EnrichedSearchResult<>(item);
            enrichedSearchResults.add(enrichedResult);
        }
    }

    private Optional<EnrichedSearchResult<Item>> getItemEnrichedSearchResult(List<EnrichedSearchResult<Item>> enrichedSearchResults, Item item) {
        return enrichedSearchResults.stream().filter(e -> e.result.equals(item)).findFirst();
    }
}
