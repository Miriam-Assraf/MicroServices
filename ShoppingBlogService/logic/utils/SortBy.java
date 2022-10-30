package acs.logic.utils;

public enum SortBy {
    EMAIL("email"),
    PRODUCT_ID("id"),
    POSTING_TIME_STAMP("postingTimeStamp"),
    LANGUAGE("language");

    private final String sortBy;

    SortBy(final String sortBy){
        this.sortBy=sortBy;
    }

    @Override
    public String toString() {
        return sortBy;
    }
}
