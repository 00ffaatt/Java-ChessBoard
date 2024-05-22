package com.example;

import java.util.Optional;
import java.util.*;

public class Square {

    private final Coordinate aCoordinate;
    private Optional<Piece> aPiece;

    /*
     * Creates a new Square on the chess board with
     * 
     * @param pCoordinate The Unique Coordinate of the square
     * 
     * @param pPiece The piece sitting on this square (cannot be empty)
     * 
     * @pre pCoordinate != null && pPiece != null;
     * 
     * @post aPiece.isPresent();
     */
    public Square(Coordinate pCoordinate, Piece pPiece) {
        assert pCoordinate != null && pPiece != null;
        aCoordinate = pCoordinate;
        aPiece = Optional.of(pPiece);
    }

    /*
     * The alternative constructor for creating a Square with no Piece
     * 
     * @pre pCoordinate != null
     * 
     * @post aPiece = Optional.empty();
     */
    public Square(Coordinate pCoordinate) {
        assert pCoordinate != null;
        aCoordinate = pCoordinate;
        aPiece = Optional.empty();
    }

    public Piece removePiece() {
        assert aPiece.isPresent();
        Optional<Piece> temp = aPiece;
        aPiece = Optional.empty();
        return temp.get();
    }

    public void addPiece(Piece pPiece) {
        assert aPiece.isEmpty();
        aPiece = Optional.of(pPiece);
    }

    public Piece getPiece() {
        return aPiece.get();
    }

    public SquareColour getSquareColour() {
        return aCoordinate.getSquareColour();
    }

    public boolean piecePresent() {
        return aPiece.isPresent();
    }

    public List<Coordinate> AccesibleMoves() {
        assert aPiece.isPresent();
        ArrayList<Coordinate> moves = new ArrayList<>();
        PieceType type = aPiece.get().getPieceType();
        Ranks rank = aCoordinate.getRank();
        Files file = aCoordinate.getFile();
        PieceColour colour = aPiece.get().getColour();

        switch (type) {
            case PAWN:
                if (colour == PieceColour.WHITE) {
                    moves.add(Coordinate.get(file, rank.getPlusOne()));

                    for (int f = Math.max(0, file.ordinal() - 1); f <= Math.min(file.ordinal() + 1, 7); f += 2) {

                    }

                    if (file == Files.H) {
                        moves.add(Coordinate.get(file.getMinusOne(), rank.getPlusOne()));
                    } else if (file == Files.A) {
                        moves.add(Coordinate.get(file.getPlusOne(), rank.getPlusOne()));
                    } else {
                        moves.add(Coordinate.get(file.getMinusOne(), rank.getPlusOne()));
                        moves.add(Coordinate.get(file.getPlusOne(), rank.getPlusOne()));
                    }
                }
                if (colour == PieceColour.BLACK) {
                    moves.add(Coordinate.get(file, rank.getMinusOne()));
                    if (file == Files.H) {
                        moves.add(Coordinate.get(file.getMinusOne(), rank.getMinusOne()));
                    } else if (file == Files.A) {
                        moves.add(Coordinate.get(file.getPlusOne(), rank.getMinusOne()));
                    } else {
                        moves.add(Coordinate.get(file.getMinusOne(), rank.getMinusOne()));
                        moves.add(Coordinate.get(file.getPlusOne(), rank.getMinusOne()));
                    }
                }

                if (rank == Ranks.TWO && colour == PieceColour.WHITE) {
                    moves.add(Coordinate.get(file, rank.getPlusOne().getPlusOne()));
                } else if (rank == Ranks.SEVEN && colour == PieceColour.BLACK) {
                    moves.add(Coordinate.get(file, rank.getMinusOne().getMinusOne()));
                }
            case KNIGHT:

            case KING:
                for (int r = Math.max(0, rank.ordinal() - 1); r <= Math.min(7, rank.ordinal() + 1); r++) {
                    for (int f = Math.max(0, file.ordinal() - 1); f <= Math.min(7,
                            Math.min(7, file.ordinal() - 1)); f++) {
                        // need to update this cuz king is special case
                        // can't take protected pieces/squares etc.

                        moves.add(Coordinate.get(Files.values()[f], Ranks.values()[r]));
                    }
                }

            case ROOK:
                for (int r = 0; r < 8 && r != rank.ordinal(); r++) {
                    moves.add(Coordinate.get(file, Ranks.values()[r]));
                }
                for (int f = 0; f < 8 && f != file.ordinal(); f++) {
                    moves.add(Coordinate.get(Files.values()[f], rank));
                }

            case BISHOP:

            case QUEEN:

        }

        return moves;
    }

}
