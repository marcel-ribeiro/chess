package com.marcel.chess.service.api.validator.move;

import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.validator.ValidationResult;

import java.util.List;

public interface MoveValidationManagementService {
    List<ValidationResult> validate(Square squareFrom, Square squareTo);
}
