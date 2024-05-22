package com.example;

public class Coordinate {

    private static final Coordinate[][] COORDINATES = new Coordinate[Files.values().length][Ranks.values().length];

    private final Files aFile;
    private final Ranks aRank;
    private final SquareColour aColour;

    // private constructor for Flyweight design pattern
    private Coordinate(Files pFile, Ranks pRank, SquareColour pColour) {
        aFile = pFile;
        aRank = pRank;
        aColour = pColour;
    }

    static {
        for (Files file : Files.values()) {
            for (Ranks rank : Ranks.values()) {
                if (file.ordinal() % 2 == rank.ordinal() % 2) {
                    COORDINATES[file.ordinal()][rank.ordinal()] = new Coordinate(file, rank, SquareColour.WHITE);
                } else {
                    COORDINATES[file.ordinal()][rank.ordinal()] = new Coordinate(file, rank, SquareColour.BLACK);
                }
            }
        }
    }

    // access method
    public static Coordinate get(Files pFile, Ranks pRank) {
        assert pFile != null && pRank != null;
        return COORDINATES[pFile.ordinal()][pRank.ordinal()];
    }

    public SquareColour getSquareColour() {
        return aColour;
    }

    public Files getFile() {
        return aFile;
    }

    public Ranks getRank() {
        return aRank;
    }

}
