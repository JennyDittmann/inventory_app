package de.homemadeapps.comparators;

import de.homemadeapps.EnrichedSearchResult;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PreferTagCountStrategyComparatorTest {
    private final EnrichedSearchResult<String> dummy = new EnrichedSearchResult<>("Dummy");
    private final EnrichedSearchResult<String> dummy2 = new EnrichedSearchResult<>("Dummy2");
    private final EnrichedSearchResult<String> dummy3 = new EnrichedSearchResult<>("Dummy3");
    private final EnrichedSearchResult<String> dummy4 = new EnrichedSearchResult<>("Dummy4");
    private final Comparator<EnrichedSearchResult<String>> preferTagCountStrategyComparator =
            new PreferTagCountStrategyComparator<>();

    @Test
    public void compareEnrichedSearchResults_AllAttributesZero_StayUnsorted() {
        List<EnrichedSearchResult<String>> expectedData = Arrays.asList(dummy,dummy2,dummy3);

        List<EnrichedSearchResult<String>> result = Arrays.asList(dummy,dummy2,dummy3);
        result.sort(preferTagCountStrategyComparator);

        assertEquals(expectedData, result);
    }

    @Test
    public void compareEnrichedSearchResults_DescriptionAttributesNonZero_SortAccordingDescriptionCount() {
        List<EnrichedSearchResult<String>> expectedData = Arrays.asList(dummy2,dummy,dummy3);

        List<EnrichedSearchResult<String>> result = Arrays.asList(dummy,dummy2,dummy3);
        for(int i = 0; i <= 5; ++i ){
            dummy2.increaseDescriptionCount();
        }
        dummy.increaseDescriptionCount();
        result.sort(preferTagCountStrategyComparator);

        assertEquals(expectedData, result);
    }

    @Test
    public void compareEnrichedSearchResults_NameAttributesNonZero_SortAccordingNameCount() {
        List<EnrichedSearchResult<String>> expectedData = Arrays.asList(dummy2,dummy,dummy3);

        List<EnrichedSearchResult<String>> result = Arrays.asList(dummy,dummy2,dummy3);
        for(int i = 0; i <= 5; ++i ){
            dummy2.increaseNameCount();
        }
        dummy.increaseNameCount();
        result.sort(preferTagCountStrategyComparator);

        assertEquals(expectedData, result);
    }

    @Test
    public void compareEnrichedSearchResults_NameAndDescriptionAttributesNonZero_SortAccordingNameBeforeDescriptionCount(){
        List<EnrichedSearchResult<String>> expectedData = Arrays.asList(dummy2,dummy4,dummy,dummy3);

        List<EnrichedSearchResult<String>> result = Arrays.asList(dummy,dummy2,dummy3,dummy4);
        for(int i = 0; i <= 10; ++i ){
            dummy2.increaseNameCount();
            dummy3.increaseDescriptionCount();
        }
        dummy.increaseNameCount();
        dummy4.increaseNameCount();
        for(int i = 0; i <= 5; ++i ){
            dummy4.increaseDescriptionCount();
        }
        result.sort(preferTagCountStrategyComparator);

        assertEquals(expectedData, result);
    }
}
