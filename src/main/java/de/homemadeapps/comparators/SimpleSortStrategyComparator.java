package de.homemadeapps.comparators;

import de.homemadeapps.EnrichedSearchResult;

import java.util.Comparator;

public class SimpleSortStrategyComparator<T> implements Comparator<EnrichedSearchResult<T>> {

    @Override
    public int compare(EnrichedSearchResult<T> o1, EnrichedSearchResult<T> o2) {
        return o2.getNameCount() - o1.getNameCount();
    }
}
