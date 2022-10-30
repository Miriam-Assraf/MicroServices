package acs.logic.utils;

public enum TimeEnum {
    LAST_DAY("lastDay"),
    LAST_WEEK("lastWeek"),
    LAST_MONTH("lastMonth");

    private final String timeEnum;

    TimeEnum(final String timeEnum){
        this.timeEnum=timeEnum;
    }

    @Override
    public String toString() {
        return timeEnum;
    }
}
