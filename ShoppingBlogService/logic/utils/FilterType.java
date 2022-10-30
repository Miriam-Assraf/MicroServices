package acs.logic.utils;

public enum FilterType {
    BY_LANGUAGE("byLanguage"),
    BY_CREATION("byCreation"),
    BY_PRODUCT("byProduct");

    private final String filterType;

    FilterType(final String filterType){
        this.filterType=filterType;
    }

    @Override
    public String toString() {
        return filterType;
    }
}

