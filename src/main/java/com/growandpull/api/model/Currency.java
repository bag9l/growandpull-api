package com.growandpull.api.model;

public enum Currency {
    // TODO: continue the list
    UAH("UAH"), USD("USD"), EUR("EUR");

    private String value;

    Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
