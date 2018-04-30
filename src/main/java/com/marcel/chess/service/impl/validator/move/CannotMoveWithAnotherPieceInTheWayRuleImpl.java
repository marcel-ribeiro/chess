package com.marcel.chess.service.impl.validator.move;

import com.google.common.collect.Sets;
import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.constants.Message;
import com.marcel.chess.model.pieces.PieceType;
import com.marcel.chess.model.validator.ValidationResult;
import com.marcel.chess.service.api.validator.move.MoveRule;
import com.marcel.chess.service.api.validator.move.MoveValidationUtil;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CannotMoveWithAnotherPieceInTheWayRuleImpl implements MoveRule {

    private MoveValidationUtil moveValidationUtil;

    public CannotMoveWithAnotherPieceInTheWayRuleImpl(MoveValidationUtil moveValidationUtil) {
        this.moveValidationUtil = moveValidationUtil;
    }

    @Override
    public ValidationResult validate(Square squareFrom, Square squareTo) {
        if (moveValidationUtil.isSomethingInTheWay(squareFrom, squareTo)) {
            return new ValidationResult(Message.MOVE_VALIDATION_CANNOT_MOVE_WITH_ANOTHER_PIECE_IN_THE_WAY, false);
        }
        return new ValidationResult(true);
    }

    @Override
    public Set<PieceType> getPieceTypes() {
        return Sets.newHashSet(PieceType.PAWN);
    }
}
