package com.marcel.chess.model.board;

import com.marcel.chess.model.pieces.Piece;
import org.springframework.core.style.ToStringCreator;

/**
 * <p>Object that represents each square on the chess board</p>
 */
public class Square {
    private int row;
    private int column;
    private Piece piece;

    /**
     * <p>Constructor that receives the coordinates (row & column) of where this square is located on the board as well as the piece placed on this square</p>
     *
     * @param row
     * @param column
     */
    public Square(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * <p>Getter</p>
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * <p>Setter</p>
     *
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * <p>Getter</p>
     *
     * @return column
     */
    public int getColumn() {
        return column;
    }

    /**
     * <p>Setter</p>
     *
     * @param column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * <p>Getter</p>
     *
     * @return piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * <p>Setter</p>
     *
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * <p>overrides the toString() method for this class returning a string with all the attributes of this objects</p>
     *
     * @return Square as a string
     */
    @Override
    public String toString() {
        ToStringCreator toStringCreator = new ToStringCreator(this);
        return toStringCreator.append("row", row).append("column", column).append("piece", piece).toString();
    }
}
