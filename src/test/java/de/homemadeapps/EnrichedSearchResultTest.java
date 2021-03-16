package de.homemadeapps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnrichedSearchResultTest {

    @Test
    public void enrichedSearchResult_CallConstructor_ReturnDefaultValues() {
        EnrichedSearchResult<String> mockData = new EnrichedSearchResult<>("Test");

        assertEquals(0, mockData.getNameCount());
        assertEquals(0, mockData.getDescriptionCount());
        assertEquals("Test", mockData.result);
    }

    @Test
    public void increaseNameCount_HavingOneCallAndGetNameCount_ReturnOne() {
        EnrichedSearchResult<String> mockData = new EnrichedSearchResult<>("Test");

        mockData.increaseNameCount();

        assertEquals(1, mockData.getNameCount());
    }

    @Test
    public void increaseDescriptionCount_HavingOneCallAndGetDescriptionCount_ReturnOne(){
        EnrichedSearchResult<String> mockData = new EnrichedSearchResult<>("Test");

        mockData.increaseDescriptionCount();

        assertEquals(1, mockData.getDescriptionCount());
    }

    @Test
    public void increaseNameCount_DoesNotChangeDescriptionCount_ReturnZero(){
        EnrichedSearchResult<String> mockData = new EnrichedSearchResult<>("Test");

        mockData.increaseNameCount();

        assertEquals(0, mockData.getDescriptionCount());
    }

    @Test
    public void increaseDescriptionCount_DoesNotChangeNameCount_ReturnZero(){
        EnrichedSearchResult<String> mockData = new EnrichedSearchResult<>("Test");

        mockData.increaseDescriptionCount();

        assertEquals(0, mockData.getNameCount());
    }

}
