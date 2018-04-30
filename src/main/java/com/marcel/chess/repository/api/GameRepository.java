package com.marcel.chess.repository.api;

import com.marcel.chess.model.board.Board;
import com.marcel.chess.model.game.GameStatus;
import com.marcel.chess.model.player.Player;

import java.util.List;

/**
 * <p>Holds all the components that make the game... the chess board, the players, the pieces, the status of the game, etc...</p>
 */
public interface GameRepository {
    /**
     * <p>Provide access to the board object</p>
     *
     * @return
     */
    Board getBoard();

    /**
     * <p>Sets up the pieces on the chess board</p>
     *
     * @param board
     */
    void setBoard(Board board);

    /**
     * <p>Provides access to the list of players</p>
     *
     * @return
     */
    List<Player> getPlayers();

    /**
     * <p>Sets the list of players</p>
     *
     * @param players
     */
    void setPlayers(List<Player> players);

    /**
     * <p>Provides access to the Game Status</p>
     *
     * @return
     */
    GameStatus getGameStatus();

    /**
     * <p>Sets the Game Statys</p>
     *
     * @param gameStatus
     */
    void setGameStatus(GameStatus gameStatus);

    /**
     * <p>Provides a way to retrieve th name of the winning player</p>
     *
     * @return winner
     */
    Player getWinner();

    /**
     * <p>Sets the winner</p>
     *
     * @param winner
     */
    void setWinner(Player winner);
}
