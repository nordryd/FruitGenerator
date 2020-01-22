package com.nordryd.fruitgen;

public enum Fruit {

    WATERMELON("\uD83C\uDF49"),
    PEACH("\uD83C\uDF51");

    private final String encoding;

    Fruit(final String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String toString() {
        return encoding;
    }
}
