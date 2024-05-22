package com.example;

public enum Files {
    A, B, C, D, E, F, G, H;

    public Files getPlusOne() {
        return Files.values()[ordinal() + 1];
    }

    public Files getMinusOne() {
        return Files.values()[ordinal() - 1];
    }
};
