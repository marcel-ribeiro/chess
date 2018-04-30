package com.marcel.chess.model.constants;

import com.marcel.chess.service.api.interaction.Color;
import com.marcel.chess.service.impl.interaction.ColorUtil;

/**
 * <p>Enum that holds all messages displayed to the user in the game</p>
 */
public enum Message {
    TEXT_SEPARATOR("--------------------------------------------\n"),
    GAME_WELCOME(TEXT_SEPARATOR + "Welcome to Marcel's Chess app"),
    GAME_INSTRUCTION("How to play?"),
    GAME_INSTRUCTION_2("This game is not a boring BLACK and WHITE game. This is more of a fun " + Color.RED.toString() + " and " + Color.GREEN.toString() + " game. Here is why.. :)"),
    GAME_INSTRUCTION_3("The first player will be " + ColorUtil.getColoredText(Color.RED.toString(), Color.RED) + " the second player will be " + ColorUtil.getColoredText(Color.GREEN.toString(), Color.GREEN)),
    GAME_CREATING_PLAYERS("Creating players..."),
    GAME_PLAYER_CREATED("Player created:"),
    GAME_PLAYER_NAME("Please enter the name of the Player:"),
    PLAYER_MOVE_INSTRUCTIONS("It is your move.\nYou are going to enter 2 values: \nThe first to select WHICH piece you want to move.\nAnd the second to select to WHERE you want to move.\nAre you ready?"),
    PLAYER_MOVE_DISPLAY_SCORE("Your current score is: "),
    PLAYER_MOVE_REQUEST_FROM("What is the piece you want to move? [I.E.: 23 - Row=2, Column=3)]"),
    PLAYER_MOVE_REQUEST_FROM_SUCCESS("The piece you selected is the "),
    PLAYER_MOVE_REQUEST_TO("Where do you want to move this piece to? [I.E.: 43 - Row=4, Column=3]"),
    PLAYER_MOVE_REQUEST_INVALID_FORMAT("Invalid Input Format. Your input must be 2 numbers between 1 and 8 representing the Row and Column of a square on the board(I.E.: 23 - Row=2, Column=3). Please try again."),
    PLAYER_MOVE_REQUEST_INVALID_EMPTY("Invalid Input Format. Your input cannot be empty. Please try again."),
    PLAYER_MOVE_REQUEST_INVALID_PIECE("Invalid piece. Either ther is no piece in the Row and Column provided or the piece in this square is not yours. Please try again."),
    PLAYER_MOVE_REQUEST_INVALID_SQUARE("Invalid move. There is already a piece there. Please try again."),
    PLAYER_MOVE_MOVEMENT_PERFORMED("Great! Movement performed!\nNext Player..."),
    PLAYER_MOVE_CAPTURE("Good job! (imagine a notification sound that sounds like you did good job sound)! You captured your opponents piece."),


    MOVE_VALIDATION_INVALID_MOVE("Invalid move. "),
    MOVE_VALIDATION_PLEASE_TRY_AGAIN("Please try again."),
    MOVE_VALIDATION_CANNOR_MOVE_BACKWARDS("This piece must move forward at all times. "),
    MOVE_VALIDATION_TO_OCCUPIED_SQUARE_SAME_PLAYER("This piece cannot occupy a square that is occupied by another piece of the same player. "),
    MOVE_VALIDATION_CANNOT_MOVE_TWO_AFTER_FIRST_MOVE("Pawns can only move 2 squares on the first move. "),
    MOVE_VALIDATION_CANNOT_MOVE_MORE_THAN_ONE_SQUARES("Cannot move more than 1 square at a time. "),
    MOVE_VALIDATION_CANNOT_MOVE_MORE_THAN_TWO_SQUARES("Cannot move more than 2 squares at a time. "),
    MOVE_VALIDATION_CANNOT_MOVE_INVALID_NUMBER_OF_SQUARES("Cannot move invaid number of squares. "),
    MOVE_VALIDATION_CANNOT_MOVE_WITH_ANOTHER_PIECE_IN_THE_WAY("Cannot move because there is another piece in the way. "),
    MOVE_VALIDATION_CANNOT_MOVE_DIAGONAL_UNLESS_CAPTURING("Cannot move diagonal unless capturing. "),
    MOVE_VALIDATION_CANNOT_MOVE_MORE_THAN_ONE_SQUARES_DIAGONAL("Cannot move more than one square diagonal."),
    MOVE_VALIDATION_CANNOT_CAPTURE_UNLESS_DIAGONALLY("Cannot move capture unless moving diagonally. "),
    MOVE_VALIDATION_CANNOT_MOVE_LSHAPE("Cannot move in L-shape."),
    MOVE_VALIDATION_CANNOT_MOVE_HORIZONTAL("Cannot move horizontally. "),
    MOVE_VALIDATION_CANNOT_MOVE_DIAGONALLY("Cannot move diagonally. "),
    MOVE_VALIDATION_CANNOT_MOVE_UNKNOWN("This is not a known / valid move. "),

    END_OF_GAME("!!!!!!!!!!!!!!!!!!!!!!!! Checkmate !!!!!!!!!!!!!!!!!!!!!!!!"),
    END_OF_GAME_WINNER("The winner is: "),
    END_OF_GAME_BOARD("This is how the chess board looks at the end of the game:");

    private String text;

    Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
