package acs.logic.utils;

public enum FilterTypePartial {
    BY_CREATION("byCreation"),
    BY_COUNT("byCount");

    private final String filterTypePartial;

    FilterTypePartial(final String filterTypePartial){
        this.filterTypePartial=filterTypePartial;
    }

    @Override
    public String toString() {
        return filterTypePartial;
    }
}