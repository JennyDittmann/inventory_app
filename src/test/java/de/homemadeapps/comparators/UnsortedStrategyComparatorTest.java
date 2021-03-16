package de.homemadeapps.comparators;

import de.homemadeapps.EnrichedSearchResult;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UnsortedStrategyComparatorTest {
    private final EnrichedSearchResult<String> dummy = new EnrichedSearchResult<>("Dummy");
    private final EnrichedSearchResult<String> dummy2 = new EnrichedSearchResult<>("Dummy2");
    private final EnrichedSearchResult<String> dummy3 = new EnrichedSearchResult<>("Dummy3");
    private final Comparator<EnrichedSearchResult<String>> unsortedStrategyComparator = new UnsortedStrategyComparator<>();

    @Test
    public void compareEnrichedSearchResults_AllAttributesZero_StayUnsorted() {
        List<EnrichedSearchResult<String>> expectedData = Arrays.asList(dummy,dummy2,dummy3);

        List<EnrichedSearchResult<String>> result = Arrays.asList(dummy,dummy2,dummy3);
        result.sort(unsortedStrategyComparator);

        assertEquals(expectedData, result);
    }

    @Test
    public void compareTwoEnrichedSearchResults_SomeAttributesNotZero_StayUnsorted(){
        List<EnrichedSearchResult<String>> expectedData = Arrays.asList(dummy3,dummy2,dummy);

        List<EnrichedSearchResult<String>> result = Arrays.asList(dummy3,dummy2,dummy);
        for(int i = 0; i < 3; ++i){
            dummy.increaseDescriptionCount();
            dummy2.increaseNameCount();
        }
        result.sort(unsortedStrategyComparator);

        assertEquals(expectedData, result);
    }
}
