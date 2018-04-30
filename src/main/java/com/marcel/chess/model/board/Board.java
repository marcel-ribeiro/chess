package com.marcel.chess.model.board;

import com.google.common.collect.ListMultimap;


/**
 * <p>Object that holds the information related to the chess board.</p>
 * <p>It holds a ListMultimap squares, which is basically a map of lists of squares.</p>
 * <p>The map holds the row indexes and the list holds the columns</p>
 */
public class Board {
    private ListMultimap<Integer, Square> squares;


    /**
     * <p>Default empty constructior</p>
     */
    public Board() {
    }

    /**
     * <p>Constructor with the ListMultimap of squares</p>
     *
     * @param squares
     */
    public Board(ListMultimap<Integer, Square> squares) {
        this.squares = squares;
    }

    /**
     * <p>Getter</p>
     *
     * @return ListMultimap<Integer, Square> squares;
     */
    public ListMultimap<Integer, Square> getSquares() {
        return squares;
    }

    /**
     * <p>Setter</p>
     *
     * @return ListMultimap<Integer, Square> squares;
     */
    public void setSquares(ListMultimap<Integer, Square> squares) {
        this.squares = squares;
    }
}
