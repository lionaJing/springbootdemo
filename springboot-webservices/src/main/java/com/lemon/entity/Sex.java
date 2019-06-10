package com.lemon.entity;

/**
 * Created by lemon
 */
public enum  Sex {
    MALE("male"),
    FEMALE("female");

    String value;

    Sex(String value) {
        this.value = value;
    }
    public String value() {
        return value;
    }
}
