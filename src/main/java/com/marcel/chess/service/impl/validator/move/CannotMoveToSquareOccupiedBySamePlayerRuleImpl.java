package com.marcel.chess.service.impl.validator.move;

import com.google.common.collect.Sets;
import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.constants.Message;
import com.marcel.chess.model.pieces.PieceType;
import com.marcel.chess.model.player.Player;
import com.marcel.chess.model.validator.ValidationResult;
import com.marcel.chess.service.api.validator.move.MoveRule;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CannotMoveToSquareOccupiedBySamePlayerRuleImpl implements MoveRule {
    @Override
    public ValidationResult validate(Square squareFrom, Square squareTo) {
        if (isSquareOccupiedBySamePlayer(squareFrom, squareTo)) {
            return new ValidationResult(Message.MOVE_VALIDATION_TO_OCCUPIED_SQUARE_SAME_PLAYER, false);

        }
        return new ValidationResult(true);
    }

    private boolean isSquareOccupiedBySamePlayer(Square squareFrom, Square squareTo) {
        if (squareTo.getPiece() == null) {
            return false;
        }

        Player playerFrom = squareFrom.getPiece().getPlayer();
        Player playerTo = squareTo.getPiece().getPlayer();
        return playerFrom.equals(playerTo);
    }

    @Override
    public Set<PieceType> getPieceTypes() {
        return Sets.newHashSet(PieceType.KING, PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT, PieceType.PAWN);
    }
}
