package com.marcel.chess.service.impl.validator.move;

import com.google.common.collect.Sets;
import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.constants.Message;
import com.marcel.chess.model.pieces.PieceType;
import com.marcel.chess.model.player.Player;
import com.marcel.chess.model.validator.ValidationResult;
import com.marcel.chess.service.api.interaction.Color;
import com.marcel.chess.service.api.validator.move.MoveRule;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CannotMoveBackwardsRuleImpl implements MoveRule {
    @Override
    public ValidationResult validate(Square squareFrom, Square squareTo) {
        if (!moveIsForward(squareFrom, squareTo)) {
            return new ValidationResult(Message.MOVE_VALIDATION_CANNOR_MOVE_BACKWARDS, false);
        }
        return new ValidationResult(true);
    }

    private boolean moveIsForward(Square squareFrom, Square squareTo) {
        Player player = squareFrom.getPiece().getPlayer();
        if (Color.RED.equals(player.getPlayerColor())) {
            return squareFrom.getRow() < squareTo.getRow();
        } else {
            return squareFrom.getRow() > squareTo.getRow();
        }
    }

    @Override
    public Set<PieceType> getPieceTypes() {
        return Sets.newHashSet(PieceType.PAWN);
    }
}
