package com.marcel.chess.model.board;

import com.marcel.chess.model.pieces.PieceType;

/**
 * <p>Object used to hold the initial configuration of the chess board.</p>
 * <p>It is very similar to a square with one key difference, it doesn't hold a Piece object but a PieceType instead. </p>
 * </p>This allow multiple pieces of the same type to be created</p>
 */
public class SquareSetupConfig {
    private PieceType pieceType;
    private int row;
    private int column;

    /**
     * Constructor
     *
     * @param pieceType
     * @param row
     * @param column
     */
    public SquareSetupConfig(PieceType pieceType, int row, int column) {
        this.pieceType = pieceType;
        this.row = row;
        this.column = column;
    }

    /**
     * <p>Getter</p>
     *
     * @return PieceType
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * <p>Setter</p>
     *
     * @param pieceType
     */
    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
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
}
