package com.marcel.chess.service.impl;

import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.constants.Message;
import com.marcel.chess.model.pieces.Piece;
import com.marcel.chess.model.player.Player;
import com.marcel.chess.model.validator.ValidationResult;
import com.marcel.chess.service.api.BoardService;
import com.marcel.chess.service.api.MoveService;
import com.marcel.chess.service.api.PlayerService;
import com.marcel.chess.service.api.interaction.Color;
import com.marcel.chess.service.api.interaction.ConsoleService;
import com.marcel.chess.service.api.validator.input.InputValidatorType;
import com.marcel.chess.service.api.validator.move.MoveValidationManagementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoveServiceImpl implements MoveService {
    private ConsoleService consoleService;
    private BoardService boardService;
    private PlayerService playerService;
    private MoveValidationManagementService moveValidationManagementService;


    public MoveServiceImpl(ConsoleService consoleService, BoardService boardService, PlayerService playerService, MoveValidationManagementService moveValidationManagementService) {
        this.consoleService = consoleService;
        this.boardService = boardService;
        this.playerService = playerService;
        this.moveValidationManagementService = moveValidationManagementService;
    }

    @Override
    public void requestMove(Player player) {
        Color playerColor = player.getPlayerColor();

        consoleService.println(Message.TEXT_SEPARATOR + player.getName(), playerColor);
        consoleService.println(Message.PLAYER_MOVE_DISPLAY_SCORE.getText() + player.getScore(), playerColor);

        boardService.printBoard();

        Square squareFrom = requestMoveFrom(player);
        Square squareTo = requestMoveTo(player);

        this.performMove(squareFrom, squareTo);
    }

    private Square requestMoveFrom(Player player) {
        Square squareFrom;
        boolean isHisOwnPiece;
        do {
            squareFrom = getMoveSquare(player.getPlayerColor(), Message.PLAYER_MOVE_REQUEST_FROM.getText());
            isHisOwnPiece = isHisOwnPiece(squareFrom, player);
            if (!isHisOwnPiece) {
                consoleService.println(Message.PLAYER_MOVE_REQUEST_INVALID_PIECE, player.getPlayerColor());
            }

        } while (!isHisOwnPiece);

        consoleService.println(Message.PLAYER_MOVE_REQUEST_FROM_SUCCESS + squareFrom.getPiece().getName(), player.getPlayerColor());

        return squareFrom;
    }

    private Square requestMoveTo(Player player) {
        Square squareTo;
        boolean isOpponentsPiece;
        do {
            squareTo = getMoveSquare(player.getPlayerColor(), Message.PLAYER_MOVE_REQUEST_TO.getText());
            isOpponentsPiece = isOpponentsPiece(squareTo, player);
            if (!isOpponentsPiece) {
                consoleService.println(Message.PLAYER_MOVE_REQUEST_INVALID_SQUARE);
            }

        } while (!isOpponentsPiece);

        return squareTo;
    }

    private boolean isOpponentsPiece(Square square, Player player) {
        return !isHisOwnPiece(square, player);
    }

    private boolean isHisOwnPiece(Square square, Player player) {
        if (square == null) {
            return false;
        }
        Piece piece = square.getPiece();
        return piece != null && piece.getPlayer().equals(player);
    }


    private Square getMoveSquare(Color playerColor, String moveRequestText) {
        String moveInput = consoleService.requestInput(moveRequestText, playerColor, InputValidatorType.INPUT_SQUARE);
        Integer row = getSquareIndexFromInput(moveInput, 0);
        Integer column = getSquareIndexFromInput(moveInput, 1);

        return boardService.getSquare(row, column);
    }

    private Integer getSquareIndexFromInput(String moveInput, int charAtPosition) {
        Character indexCharacter = moveInput.charAt(charAtPosition);
        Integer index = new Integer(indexCharacter.toString());
        if (index > 0) {
            index--;
        }
        return index;
    }

    @Override
    public void performMove(Square squareFrom, Square squareTo) {
        boolean isValidMove = true;

        List<ValidationResult> validationResults = moveValidationManagementService.validate(squareFrom, squareTo);

        for (ValidationResult validationResult : validationResults) {
            if (!validationResult.isValid()) {
                consoleService.println(Message.MOVE_VALIDATION_INVALID_MOVE.getText() + validationResult.getMessage());
                isValidMove = false;
            }
        }

        if (isValidMove) {
            proceedWithMove(squareFrom, squareTo);
        } else {
            consoleService.println(Message.MOVE_VALIDATION_PLEASE_TRY_AGAIN);
            requestANewMove(squareFrom);
        }


    }

    private void proceedWithMove(Square squareFrom, Square squareTo) {
        Piece pieceFrom = squareFrom.getPiece();
        Piece pieceTo = squareTo.getPiece();
        Player playerFrom = pieceFrom.getPlayer();

        //TODO: Check for null pointers and invalid moves
        if (pieceTo != null && !playerFrom.equals(pieceTo.getPlayer())) {
            consoleService.println(Message.PLAYER_MOVE_CAPTURE, playerFrom.getPlayerColor());
            playerFrom.increaseScore(pieceTo.getPieceType().getPoints());
            playerService.updatePlayerScore(playerFrom);
        }

        squareTo.setPiece(pieceFrom);
        squareFrom.setPiece(null);

        consoleService.println(Message.PLAYER_MOVE_MOVEMENT_PERFORMED, playerFrom.getPlayerColor());
    }


    private void requestANewMove(Square squareFrom) {
        Piece pieceFrom = squareFrom.getPiece();
        Player playerFrom = pieceFrom.getPlayer();
        requestMove(playerFrom);
    }
}
