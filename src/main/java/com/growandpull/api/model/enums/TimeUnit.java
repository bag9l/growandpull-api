package com.growandpull.api.model.enums;

public enum TimeUnit {
    SECOND("sec"),
    MINUTE("min"),
    HOUR("hr"),
    DAY("d"),
    WEEK("wk"),
    MONTH("mos"),
    YEAR("yr");

    private final String value;

    TimeUnit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
