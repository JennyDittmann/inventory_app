package de.homemadeapps.comparators;

import de.homemadeapps.EnrichedSearchResult;

import java.util.Comparator;

public class UnsortedStrategyComparator <T> implements Comparator<EnrichedSearchResult<T>> {
    @Override
    public int compare(EnrichedSearchResult<T> o1, EnrichedSearchResult<T> o2) {
        return 0;
    }

}
