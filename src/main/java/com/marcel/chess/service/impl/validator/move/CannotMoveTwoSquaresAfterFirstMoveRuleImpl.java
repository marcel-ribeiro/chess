package com.marcel.chess.service.impl.validator.move;

import com.google.common.collect.Sets;
import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.constants.GameConfig;
import com.marcel.chess.model.constants.Message;
import com.marcel.chess.model.pieces.PieceType;
import com.marcel.chess.model.player.Player;
import com.marcel.chess.model.validator.ValidationResult;
import com.marcel.chess.service.api.interaction.Color;
import com.marcel.chess.service.api.validator.move.MoveRule;
import com.marcel.chess.service.api.validator.move.MoveValidationUtil;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CannotMoveTwoSquaresAfterFirstMoveRuleImpl implements MoveRule {

    private MoveValidationUtil moveValidationUtil;

    public CannotMoveTwoSquaresAfterFirstMoveRuleImpl(MoveValidationUtil moveValidationUtil) {
        this.moveValidationUtil = moveValidationUtil;
    }

    @Override
    public ValidationResult validate(Square squareFrom, Square squareTo) {
        if (isNotAtPawnsRow(squareFrom, squareTo) && isMovingTwoSquares(squareFrom, squareTo)) {
            return new ValidationResult(Message.MOVE_VALIDATION_CANNOT_MOVE_TWO_AFTER_FIRST_MOVE, false);

        }
        return new ValidationResult(true);
    }


    private boolean isNotAtPawnsRow(Square squareFrom, Square squareTo) {
        Player player = squareFrom.getPiece().getPlayer();
        if (Color.RED.equals(player.getPlayerColor())) {
            return squareFrom.getRow() != GameConfig.PAWNS_ROW_RED;
        } else {
            return squareFrom.getRow() != GameConfig.PAWNS_ROW_GREEN;
        }
    }

    private boolean isMovingTwoSquares(Square squareFrom, Square squareTo) {
        return moveValidationUtil.getNumberOfSquaresMoved(squareFrom, squareTo) == 2;
    }


    @Override
    public Set<PieceType> getPieceTypes() {
        return Sets.newHashSet(PieceType.PAWN);
    }
}
