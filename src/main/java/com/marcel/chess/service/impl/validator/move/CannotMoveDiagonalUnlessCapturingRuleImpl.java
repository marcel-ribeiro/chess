package com.marcel.chess.service.impl.validator.move;

import com.google.common.collect.Sets;
import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.constants.Message;
import com.marcel.chess.model.pieces.PieceType;
import com.marcel.chess.model.validator.MoveType;
import com.marcel.chess.model.validator.ValidationResult;
import com.marcel.chess.service.api.validator.move.MoveRule;
import com.marcel.chess.service.api.validator.move.MoveValidationUtil;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CannotMoveDiagonalUnlessCapturingRuleImpl implements MoveRule {

    private MoveValidationUtil moveValidationUtil;

    public CannotMoveDiagonalUnlessCapturingRuleImpl(MoveValidationUtil moveValidationUtil) {
        this.moveValidationUtil = moveValidationUtil;
    }

    @Override
    public ValidationResult validate(Square squareFrom, Square squareTo) {


        MoveType moveType = moveValidationUtil.getMoveType(squareFrom, squareTo);
        boolean isCapturing = moveValidationUtil.isCapturing(squareFrom, squareTo);
        if (MoveType.DIAGONAL.equals(moveType) && !isCapturing) {
            return new ValidationResult(Message.MOVE_VALIDATION_CANNOT_MOVE_DIAGONAL_UNLESS_CAPTURING, false);
        }
        return new ValidationResult(true);
    }

    @Override
    public Set<PieceType> getPieceTypes() {
        return Sets.newHashSet(PieceType.PAWN);
    }
}
