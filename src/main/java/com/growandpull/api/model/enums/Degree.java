package com.growandpull.api.model.enums;

public enum Degree {
    BACHELOR("бакалавр"),
    MASTER("магістр");

    private final String value;

    Degree(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
