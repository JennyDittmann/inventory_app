package de.homemadeapps.controller;

import de.homemadeapps.manager.ItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ItemController {
    private final ItemManager itemManager;

    @Autowired
    public ItemController(final ItemManager itemManager){
        this.itemManager = itemManager;
    }

    @GetMapping(value = "addItem", produces = "application/json")
    public Map<String,Integer> addItem(final String name, final String description) {
        Map<String,Integer> response = new HashMap<>();

        try{
            if(itemManager.saveItem(name,description)){
                response.put("responseCode", 201);
            }else{
                response.put("responseCode", 417);
            }
        }catch (Exception e){
            response.put("responseCode",500);
        }

        return response;
    }

    @GetMapping(value = "updateItem", produces = "application/json")
    public Map<String, Integer> updateItem(final int id, String name, final String description) {
        Map<String,Integer> response = new HashMap<>();

        try{
            if(itemManager.updateItem(id, name,description)){
                response.put("responseCode", 200);
            }else{
                response.put("responseCode", 417);
            }
        }catch (Exception e){
            response.put("responseCode",500);
        }

        return response;
    }

    @GetMapping(value = "deleteItem", produces = "application/json")
    public Map<String,Integer> deleteItem(final int id){
        Map<String,Integer> response = new HashMap<>();

        try{
            if(itemManager.deleteItem(id)){
                response.put("responseCode",200);
            }else{
                response.put("responseCode",417);
            }
        }catch (Exception e){
            response.put("responseCode",500);
        }

        return response;
    }
}
