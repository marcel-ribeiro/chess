package com.marcel.chess.model.pieces;

import org.springframework.core.style.ToStringCreator;

/**
 * <p>Enum that holds the information related to a PieceType (King, Queen, Rook, etc...)</p>
 * <p>It provides:</p>
 * <p>- The name of the piece</p>
 * <p>- The Symbol that represents that piece</p>
 * <p>- The number of points scored when a player captures this piece</p>
 */
public enum PieceType {
    KING("King", 'K', 100),
    QUEEN("Queen", 'Q', 9),
    ROOK("Rook", 'R', 5),
    BISHOP("Bishop", 'B', 3),
    KNIGHT("Knight", 'N', 3),
    PAWN("Pawn", 'P', 1);

    private String name;
    private char symbol;
    private int points;


    /**
     * <p>Constructor</p>
     *
     * @param name
     * @param symbol
     * @param points
     */
    PieceType(String name, char symbol, int points) {
        this.name = name;
        this.symbol = symbol;
        this.points = points;
    }

    /**
     * <p>Getter</p>
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Getter</p>
     *
     * @return symbol
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * <p>Getter</p>
     *
     * @return points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Overrides the toString() method to display all the information for thar particular piece
     *
     * @return
     */
    @Override
    public String toString() {
        ToStringCreator toStringCreator = new ToStringCreator(this);
        return toStringCreator.append("name", name).append("symbol", symbol).append("points", symbol).toString();
    }
}