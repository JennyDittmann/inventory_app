package de.homemadeapps;

/**
 * Select preferred strategy for result list order.
 */
public enum SearchStrategy {
    /**
     * Not ordered.
     */
    UNSORTED,
    /**
     * Prefer match in tag name over tag description.
     */
    SORTED,
    /**
     * Prefer match in tag name over tag description and weight result of search term occurrences in different matching
     * tags.
     */
     PREFER_TAG_COUNT
}
