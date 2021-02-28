package de.homemadeapps;

import java.util.Objects;

public class EnrichedSearchResult<T> {

    public final T result;
    private int nameCount;
    private int descriptionCount;

    public EnrichedSearchResult(T result) {
        this.result = result;
        this.nameCount = 0;
        this.descriptionCount = 0;
    }

    public void increaseNameCount() {
        nameCount++;
    }

    public void increaseDescriptionCount() {
        descriptionCount++;
    }

    public int getNameCount() {
        return nameCount;
    }

    public int getDescriptionCount() {
        return descriptionCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrichedSearchResult<?> that = (EnrichedSearchResult<?>) o;
        return nameCount == that.nameCount && descriptionCount == that.descriptionCount && Objects.equals(result, that.result);
    }
}
