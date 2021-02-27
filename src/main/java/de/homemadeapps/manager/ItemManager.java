package de.homemadeapps.manager;

import de.homemadeapps.databaseSchemas.Item;
import de.homemadeapps.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemManager {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemManager(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public boolean saveItem(final String name, final String description) {
        boolean success = false;
        boolean doExist = false;
        Item item = new Item();
        item.setName(name);
        item.setName(description);
        List<Item> smallItemRepo = itemRepository.findItemsByName(name);

        for (Item filteredItem : smallItemRepo) {
            if (filteredItem.getDescription().equals(description)) {
                doExist = true;
                break;
            }
        }
        if (!doExist) {
            itemRepository.save(item);
            success = true;
        }

        return success;
    }

    public boolean updateItem(final int id, final String name, final String description) {
        boolean success = false;

        if (itemRepository.findById(id).isPresent()) {
            final Item item = new Item(name, description);
            itemRepository.save(item);
            success = true;
        }

        return success;
    }

    public List<Item> searchItemsByNameAndDescription(final String query) {
        List<Item> resultList = new ArrayList<>(itemRepository.findItemsByName(query));
        List<Item> resultListByDescription = new ArrayList<>(itemRepository.findItemsByDescription(query));

        for (Item item : resultListByDescription) {
            if (!resultList.contains(item)) {
                resultList.add(item);
            }
        }

        return resultList;
    }

    public boolean deleteItem(Item item) {
        boolean success = false;
        Optional<Item> itemFromDatabase = itemRepository.findById(item.getId());
        boolean itemIsPresent = itemFromDatabase.isPresent();

        if (itemIsPresent && itemFromDatabase.get().equals(item)) {
            itemRepository.delete(item);
            success = true;
        }

        return success;
    }
}
