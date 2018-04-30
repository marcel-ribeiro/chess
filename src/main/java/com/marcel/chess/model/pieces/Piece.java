package com.marcel.chess.model.pieces;

import com.marcel.chess.model.player.Player;
import org.springframework.core.style.ToStringCreator;

/**
 * <p>Object that represents a piece in the game</p>
 * <p>Once a piece is created with it's attributes it never changes.</p>
 */
public class Piece {

    private PieceType pieceType;
    private Player player;

    /**
     * <p>Constructor</p>
     *
     * @param pieceType
     * @param player
     */
    public Piece(PieceType pieceType, Player player) {
        if (pieceType == null || player == null) {
            throw new IllegalArgumentException("The PieceType cannot be null and the Player cannot be null");
        }

        this.pieceType = pieceType;
        this.player = player;
    }

    /**
     * <p>Getter</p>
     *
     * @return pieceType
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * <p>Getter</p>
     * <p>Returns the name that represents the piece when it has to be displayed to the user</p>
     *
     * @return pieceType
     */
    public String getName() {
        return this.pieceType.getName();
    }

    /**
     * <p>Getter</p>
     * <p>Returns the symbol that represents the piece on the board</p>
     *
     * @return symbol
     */
    public char getSymbol() {
        return this.pieceType.getSymbol();
    }

    /**
     * <p>Getter</p>
     * <p>Returns the player that owns this piece on the board</p>
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Overrides the toString() method to display the piece's properties
     *
     * @return piece as a string
     */
    @Override
    public String toString() {
        ToStringCreator toStringCreator = new ToStringCreator(this);
        return toStringCreator.append("player", player).append("pieceType", pieceType).toString();
    }
}
