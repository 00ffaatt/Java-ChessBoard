package com.example;

public class Piece {

    private final PieceType aPieceType;
    private final PieceColour aPieceColour;

    public Piece(PieceType pPieceType, PieceColour pPieceColour) {
        assert pPieceType != null && pPieceColour != null;
        aPieceType = pPieceType;
        aPieceColour = pPieceColour;
    }

    public PieceType getPieceType() {
        return aPieceType;
    }

    public PieceColour getColour() {
        return aPieceColour;
    }

}
