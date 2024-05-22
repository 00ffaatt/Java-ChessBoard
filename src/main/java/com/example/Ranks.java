package com.example;

public enum Ranks {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT;

    public Ranks getPlusOne() {
        return Ranks.values()[ordinal() + 1];
    }

    public Ranks getMinusOne() {
        return Ranks.values()[ordinal() - 1];
    }
};
