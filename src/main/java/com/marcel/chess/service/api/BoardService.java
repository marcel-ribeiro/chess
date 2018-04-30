package com.marcel.chess.service.api;

import com.marcel.chess.model.board.Square;

public interface BoardService {
    void createBoard();

    void setBoard();

    void printBoard();

    void printBoardWithoutPieces();

    void printBoardAsList();

    Square getSquare(Integer row, Integer column);
}
