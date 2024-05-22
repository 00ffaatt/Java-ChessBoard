package com.example;

public class Board {

    private static final Board INSTANCE = new Board();
    private Square[][] aSquares = new Square[8][8];

    /*
     * private constructor for SINGLETON design pattern
     * - only 1 instance of board is needed to represent a game of chess
     * 
     * initializes a chess Board with pieces in their starting position
     */
    private Board() {
        PieceType[] pieceOrder = new PieceType[] {
                PieceType.ROOK,
                PieceType.KNIGHT,
                PieceType.BISHOP,
                PieceType.QUEEN,
                PieceType.KING,
                PieceType.BISHOP,
                PieceType.KNIGHT,
                PieceType.ROOK
        };

        for (Ranks rank : Ranks.values()) {
            for (Files file : Files.values()) {
                if (rank == Ranks.ONE) {
                    aSquares[rank.ordinal()][file.ordinal()] = (new Square(Coordinate.get(file, rank),
                            new Piece(pieceOrder[file.ordinal()], PieceColour.WHITE)));
                } else if (rank == Ranks.TWO) {
                    aSquares[rank.ordinal()][file.ordinal()] = (new Square(Coordinate.get(file, rank),
                            new Piece(PieceType.PAWN, PieceColour.WHITE)));
                } else if (rank == Ranks.SEVEN) {
                    aSquares[rank.ordinal()][file.ordinal()] = (new Square(Coordinate.get(file, rank),
                            new Piece(PieceType.PAWN, PieceColour.BLACK)));
                } else if (rank == Ranks.EIGHT) {
                    aSquares[rank.ordinal()][file.ordinal()] = (new Square(Coordinate.get(file, rank),
                            new Piece(pieceOrder[file.ordinal()], PieceColour.BLACK)));
                } else {
                    aSquares[rank.ordinal()][file.ordinal()] = (new Square(Coordinate.get(file, rank)));
                }
            }
        }
    }

    /*
     * @return the unique instance of the board
     */
    public static Board instance() {
        return INSTANCE;
    }

    public Square getSquare(int pRank, int pFile) {
        assert pRank >= 0 && pRank < 8;
        assert pFile >= 0 && pFile < 8;
        return aSquares[pRank][pFile];
    }

    public Square getSquare(Ranks pRank, Files pFile) {
        assert pRank != null && pFile != null;
        return aSquares[pRank.ordinal()][pFile.ordinal()];
    }

}
