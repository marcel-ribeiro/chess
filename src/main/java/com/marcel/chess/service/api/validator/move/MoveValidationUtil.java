package com.marcel.chess.service.api.validator.move;

import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.validator.MoveDirection;
import com.marcel.chess.model.validator.MoveType;

public interface MoveValidationUtil {
    MoveType getMoveType(Square squareFrom, Square squareTo);

    MoveDirection getMoveDirectionRow(Square squareFrom, Square squareTo);

    MoveDirection getMoveDirectionColumn(Square squareFrom, Square squareTo);

    boolean isSomethingInTheWay(Square squareFrom, Square squareTo);

    boolean isCapturing(Square squareFrom, Square squareTo);

    Integer getNumberOfSquaresMoved(Square squareFrom, Square squareTo);
}
