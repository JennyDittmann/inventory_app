package de.homemadeapps.comparators;

import de.homemadeapps.EnrichedSearchResult;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleSortStrategyComparatorTest {
    private final EnrichedSearchResult<String> dummy = new EnrichedSearchResult<>("Dummy");
    private final EnrichedSearchResult<String> dummy2 = new EnrichedSearchResult<>("Dummy2");
    private final EnrichedSearchResult<String> dummy3 = new EnrichedSearchResult<>("Dummy3");
    private final Comparator<EnrichedSearchResult<String>> simpleSortStrategyComparator =
            new SimpleSortStrategyComparator<>();

    @Test
    public void compareEnrichedSearchResults_AllAttributesZero_StayUnsorted() {
        List<EnrichedSearchResult<String>> expectedData = Arrays.asList(dummy,dummy2,dummy3);

        List<EnrichedSearchResult<String>> result = Arrays.asList(dummy,dummy2,dummy3);
        result.sort(simpleSortStrategyComparator);

        assertEquals(expectedData, result);
    }

    @Test
    public void compareEnrichedSearchResults_DescriptionAttributesNonZero_StayUnsorted() {
        List<EnrichedSearchResult<String>> expectedData = Arrays.asList(dummy,dummy2,dummy3);

        List<EnrichedSearchResult<String>> result = Arrays.asList(dummy,dummy2,dummy3);
        for(int i = 0; i <= 5; ++i ){
            dummy2.increaseDescriptionCount();
        }
        dummy.increaseDescriptionCount();
        result.sort(simpleSortStrategyComparator);

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
        result.sort(simpleSortStrategyComparator);

        assertEquals(expectedData, result);
    }
}
