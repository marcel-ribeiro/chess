package com.marcel.chess.service.impl.validator.move;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.MultimapBuilder;
import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.pieces.PieceType;
import com.marcel.chess.model.validator.ValidationResult;
import com.marcel.chess.service.api.validator.move.MoveRule;
import com.marcel.chess.service.api.validator.move.MoveValidationManagementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MoveValidationManagementServiceImpl implements MoveValidationManagementService {
    private ListMultimap<PieceType, MoveRule> validatorServiceListMultimap;

    public MoveValidationManagementServiceImpl(List<MoveRule> validatorServiceList) {
        this.validatorServiceListMultimap =
                MultimapBuilder.treeKeys().arrayListValues().build();

        for (MoveRule moveRule : validatorServiceList) {
            Set<PieceType> pieceTypes = moveRule.getPieceTypes();
            for (PieceType pieceType : pieceTypes) {
                this.validatorServiceListMultimap.put(pieceType, moveRule);
            }
        }
    }

    @Override
    public List<ValidationResult> validate(Square squareFrom, Square squareTo) {
        List<ValidationResult> validationResults = Lists.newArrayList();
        PieceType pieceTypeFrom = squareFrom.getPiece().getPieceType();
        List<MoveRule> moveRules = this.validatorServiceListMultimap.get(pieceTypeFrom);

        for (MoveRule moveRule : moveRules) {
            ValidationResult validationResult = moveRule.validate(squareFrom, squareTo);
            validationResults.add(validationResult);
        }

        return validationResults;
    }
}
