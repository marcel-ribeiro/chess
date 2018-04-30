package com.marcel.chess.service.api.validator.move;

import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.pieces.PieceType;
import com.marcel.chess.model.validator.ValidationResult;

import java.util.Set;

/**
 * <p>Interface for all the rules that determine how the pieces can or cannot m</p>
 */
public interface MoveRule {
    ValidationResult validate(Square squareFrom, Square squareTo);

    Set<PieceType> getPieceTypes();
}
