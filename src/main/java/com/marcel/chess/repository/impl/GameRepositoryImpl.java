package com.marcel.chess.repository.impl;

import com.marcel.chess.model.board.Board;
import com.marcel.chess.model.game.GameStatus;
import com.marcel.chess.model.player.Player;
import com.marcel.chess.repository.api.GameRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepositoryImpl implements GameRepository {
    private Board board;
    private GameStatus gameStatus;
    private List<Player> players;
    private Player winner;

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
