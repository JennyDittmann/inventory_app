package de.homemadeapps.comparators;

import de.homemadeapps.EnrichedSearchResult;

import java.util.Comparator;

public class PreferTagCountStrategyComparator<T> implements Comparator<EnrichedSearchResult<T>> {

    @Override
    public int compare(EnrichedSearchResult<T> o1, EnrichedSearchResult<T> o2) {
        int result = 0;
        if (o1.getNameCount() > o2.getNameCount()) {
            result = -1;
        } else if (o1.getNameCount() < o2.getNameCount()) {
            result = 1;
        } else {
            if (o1.getDescriptionCount() > o2.getDescriptionCount()) {
                result = -1;
            } else if (o1.getDescriptionCount() < o2.getDescriptionCount()) {
                result = 1;
            }
        }
        return result;
    }
}
