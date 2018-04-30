package com.marcel.chess.service.impl.validator.move;

import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.constants.GameConfig;
import com.marcel.chess.model.player.Player;
import com.marcel.chess.model.validator.MoveDirection;
import com.marcel.chess.model.validator.MoveType;
import com.marcel.chess.service.api.BoardService;
import com.marcel.chess.service.api.validator.move.MoveValidationUtil;
import org.springframework.stereotype.Component;

@Component
public class MoveValidationUtilImpl implements MoveValidationUtil {

    private BoardService boardService;

    public MoveValidationUtilImpl(BoardService boardService) {
        this.boardService = boardService;
    }

    public MoveType getMoveType(Square squareFrom, Square squareTo) {
        if (isSameRow(squareFrom, squareTo) && !isSameColumn(squareFrom, squareTo)) {
            return MoveType.HORIZONTAL;
        } else if (!isSameRow(squareFrom, squareTo) && isSameColumn(squareFrom, squareTo)) {
            return MoveType.VERTICAL;
        } else if (isDiagonalMove(squareFrom, squareTo)) {
            return MoveType.DIAGONAL;
        } else if (isLShapeMove(squareFrom, squareTo)) {
            return MoveType.L_SHAPE;
        } else {
            return MoveType.UNKNOWN;
        }
    }

    private boolean isSameRow(Square squareFrom, Square squareTo) {
        return squareFrom.getRow() == squareTo.getRow();
    }

    private boolean isSameColumn(Square squareFrom, Square squareTo) {
        return squareFrom.getColumn() == squareTo.getColumn();
    }

    private boolean isDiagonalMove(Square squareFrom, Square squareTo) {
        if (isSameRow(squareFrom, squareTo) || isSameColumn(squareFrom, squareTo)) {
            return false;
        }

        int rowDiff = Math.abs(squareFrom.getRow() -
                squareTo.getRow());
        int collumnDiff = Math.abs(squareFrom.getColumn() - squareTo.getColumn());

        return rowDiff == collumnDiff;
    }

    private boolean isLShapeMove(Square squareFrom, Square squareTo) {
        int rowDiff = Math.abs(squareFrom.getRow() - squareTo.getRow());
        int collumnDiff = Math.abs(squareFrom.getColumn() - squareTo.getColumn());

        return (rowDiff == 2 && collumnDiff == 1) || (rowDiff == 1 && collumnDiff == 2);
    }

    @Override
    public MoveDirection getMoveDirectionRow(Square squareFrom, Square squareTo) {
        return squareFrom.getRow() > squareTo.getRow() ? MoveDirection.GREATER_TO_LOWER : MoveDirection.LOWER_TO_GREATER;
    }

    @Override
    public MoveDirection getMoveDirectionColumn(Square squareFrom, Square squareTo) {
        return squareFrom.getColumn() > squareTo.getColumn() ? MoveDirection.GREATER_TO_LOWER : MoveDirection.LOWER_TO_GREATER;
    }

    @Override
    public boolean isSomethingInTheWay(Square squareFrom, Square squareTo) {
        MoveType moveType = getMoveType(squareFrom, squareTo);
        switch (moveType) {
            case HORIZONTAL:
                return isSomethingInTheWayHorizontal(squareFrom, squareTo);
            case VERTICAL:
                return isSomethingInTheWayVertical(squareFrom, squareTo);
            case DIAGONAL:
                return isSomethingInTheWayDiagonal(squareFrom, squareTo);
            case L_SHAPE:
                return isSomethingInTheWayLShape(squareFrom, squareTo);

            default:
                return true;
        }

    }

    private boolean isSomethingInTheWayHorizontal(Square squareFrom, Square squareTo) {
        int row = squareFrom.getRow();
        int columnStartChecking;
        int columnStopChecking;

        MoveDirection moveDirectionColumn = getMoveDirectionColumn(squareFrom, squareTo);

        if (MoveDirection.GREATER_TO_LOWER.equals(moveDirectionColumn)) {
            columnStartChecking = squareTo.getColumn();
            columnStopChecking = squareFrom.getColumn();
        } else {
            columnStartChecking = squareFrom.getColumn();
            columnStopChecking = squareTo.getColumn();
        }


        for (int column = ++columnStartChecking; column < columnStopChecking; column++) {
            Square squareChecking = boardService.getSquare(row, column);
            if (squareChecking.getPiece() != null) {
                return true;
            }
        }

        return false;
    }

    private boolean isSomethingInTheWayVertical(Square squareFrom, Square squareTo) {
        int column = squareFrom.getColumn();
        int rowStartChecking;
        int rowStopChecking;

        MoveDirection moveDirectionRow = getMoveDirectionRow(squareFrom, squareTo);

        if (MoveDirection.GREATER_TO_LOWER.equals(moveDirectionRow)) {
            rowStartChecking = squareTo.getRow();
            rowStopChecking = squareFrom.getRow();
        } else {
            rowStartChecking = squareFrom.getRow();
            rowStopChecking = squareTo.getRow();
        }


        for (int row = ++rowStartChecking; row < rowStopChecking; row++) {
            Square squareChecking = boardService.getSquare(row, column);
            if (squareChecking.getPiece() != null) {
                return true;
            }
        }

        return false;
    }

    private boolean isSomethingInTheWayDiagonal(Square squareFrom, Square squareTo) {
        int rowStartChecking;
        int rowStopChecking;
        int columnStartChecking;
        int columnStopChecking;

        MoveDirection moveDirectionRow = getMoveDirectionRow(squareFrom, squareTo);
        MoveDirection moveDirectionColumn = getMoveDirectionColumn(squareFrom, squareTo);


        if (MoveDirection.GREATER_TO_LOWER.equals(moveDirectionRow)) {
            rowStartChecking = squareTo.getRow();
            rowStopChecking = squareFrom.getRow();
        } else {
            rowStartChecking = squareFrom.getRow();
            rowStopChecking = squareTo.getRow();
        }

        if (MoveDirection.GREATER_TO_LOWER.equals(moveDirectionColumn)) {
            columnStartChecking = squareTo.getColumn();
            columnStopChecking = squareFrom.getColumn();
        } else {
            columnStartChecking = squareFrom.getColumn();
            columnStopChecking = squareTo.getColumn();
        }


        for (int row = ++rowStartChecking, column = ++columnStartChecking; row < rowStopChecking || column < columnStopChecking; row++, column++) {
            Square squareChecking = boardService.getSquare(row, column);
            if (squareChecking.getPiece() != null) {
                return true;
            }
        }

        return false;
    }

    private boolean isSomethingInTheWayLShape(Square squareFrom, Square squareTo) {
        //TODO: implement method
        return false;
    }

    @Override
    public boolean isCapturing(Square squareFrom, Square squareTo) {
        if (squareFrom == null || squareFrom.getPiece() == null || squareFrom.getPiece().getPlayer() == null || squareTo == null || squareTo.getPiece() == null || squareTo.getPiece().getPlayer() == null) {
            return false;
        }

        Player playerFrom = squareFrom.getPiece().getPlayer();
        Player playerTo = squareTo.getPiece().getPlayer();
        return !playerFrom.equals(playerTo);
    }

    @Override
    public Integer getNumberOfSquaresMoved(Square squareFrom, Square squareTo) {
        MoveType moveType = getMoveType(squareFrom, squareTo);
        switch (moveType) {
            case HORIZONTAL:
                return getNumberOfSquaresMovedHorizontal(squareFrom, squareTo);
            case VERTICAL:
                return getNumberOfSquaresMovedVertical(squareFrom, squareTo);
            case DIAGONAL:
                return getNumberOfSquaresMovedDiagonal(squareFrom, squareTo);
            case L_SHAPE:
                return getNumberOfSquaresMovedShape(squareFrom, squareTo);
            default:
                return GameConfig.INVALID_NUMBER_OF_SQUARES_MOVED;
        }
    }

    private Integer getNumberOfSquaresMovedHorizontal(Square squareFrom, Square squareTo) {
        return Math.abs(squareTo.getColumn() - squareFrom.getColumn());
    }

    private Integer getNumberOfSquaresMovedVertical(Square squareFrom, Square squareTo) {
        return Math.abs(squareTo.getRow() - squareFrom.getRow());
    }

    private Integer getNumberOfSquaresMovedDiagonal(Square squareFrom, Square squareTo) {
        //For diagonal moves the number of rows and columns moved should be the same
        return Math.abs(squareTo.getRow() - squareFrom.getRow());
    }

    private Integer getNumberOfSquaresMovedShape(Square squareFrom, Square squareTo) {
        int rowDiff = Math.abs(squareFrom.getRow() - squareTo.getRow());
        int collumnDiff = Math.abs(squareFrom.getColumn() - squareTo.getColumn());

        return rowDiff + collumnDiff;
    }


}