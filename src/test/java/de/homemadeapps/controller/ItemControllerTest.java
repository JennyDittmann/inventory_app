package de.homemadeapps.controller;

import de.homemadeapps.manager.ItemManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTest {
    @Mock
    private ItemManager itemManager;
    private ItemController itemController;

    @Before
    public void setUp() {
        itemManager = mock(ItemManager.class);
        itemController = new ItemController(itemManager);
    }

    @Test
    public void addItem_AddedSuccessfully_ReturnRC200() {
        when(itemManager.saveItem("name","description")).thenReturn(true);

        Map<String, String> result = itemController.addItem("name", "description");

        assertEquals("201", result.get("responseCode"));
    }

    @Test
    public void addItem_OnDataAlreadyExisted_ReturnRC417(){
        when(itemManager.saveItem("name","description")).thenReturn(false);

        Map<String, String> result = itemController.addItem("name", "description");

        assertEquals("417", result.get("responseCode"));
    }

    @Test
    public void addItem_OnInternalError_ReturnRC500(){
        when(itemManager.saveItem("name","description")).thenThrow();

        Map<String, String> result = itemController.addItem("name", "description");

        assertEquals("500", result.get("responseCode"));
    }

    @Test
    public void updateItem_IfSuccessfully_ReturnRC200(){
        when(itemManager.updateItem(1,"name","description")).thenReturn(true);

        Map<String,String> result = itemController.updateItem(1,"name","description");

        assertEquals("200",result.get("responseCode"));
    }

    @Test
    public void updateItem_IfNotSuccessfully_ReturnRC417(){
        when(itemManager.updateItem(1,"name","description")).thenReturn(false);

        Map<String,String> result = itemController.updateItem(1,"name","description");

        assertEquals("417",result.get("responseCode"));
    }

    @Test
    public void updateItem_OnInternalError_ReturnRC500(){
        when(itemManager.updateItem(1,"name","description")).thenThrow();

        Map<String,String> result = itemController.updateItem(1,"name","description");

        assertEquals("500",result.get("responseCode"));
    }
}
