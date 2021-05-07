package de.homemadeapps.controller;

import de.homemadeapps.manager.ItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ItemController {
    private final ItemManager itemManager;

    @Autowired
    public ItemController(final ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @PostMapping(value = "/addItem", produces = "application/json")
    public Map<String, Integer> addItem(final String name, final String description) {
        Map<String, Integer> response = new HashMap<>();

        try {
            if (itemManager.saveItem(name, description)) {
                response.put("status", 201);
            } else {
                response.put("status", 417);
            }
        } catch (Exception e) {
            response.put("status", 500);
        }

        return response;
    }

    @PostMapping(value = "/updateItem", produces = "application/json")
    public Map<String, Integer> updateItem(final int id, String name, final String description) {
        Map<String, Integer> response = new HashMap<>();

        try {
            if (itemManager.updateItem(id, name, description)) {
                response.put("status", 200);
            } else {
                response.put("status", 417);
            }
        } catch (Exception e) {
            response.put("status", 500);
        }

        return response;
    }

    @PostMapping(value = "/deleteItem", produces = "application/json")
    public Map<String, Integer> deleteItem(final int id) {
        Map<String, Integer> response = new HashMap<>();

        try {
            if (itemManager.deleteItem(id)) {
                response.put("status", 200);
            } else {
                response.put("status", 417);
            }
        } catch (Exception e) {
            response.put("status", 500);
        }

        return response;
    }
}