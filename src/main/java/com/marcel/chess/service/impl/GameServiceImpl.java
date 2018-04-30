package com.marcel.chess.service.impl;

import com.marcel.chess.model.constants.Message;
import com.marcel.chess.model.game.GameStatus;
import com.marcel.chess.model.player.Player;
import com.marcel.chess.repository.api.GameRepository;
import com.marcel.chess.service.api.BoardService;
import com.marcel.chess.service.api.GameService;
import com.marcel.chess.service.api.MoveService;
import com.marcel.chess.service.api.PlayerService;
import com.marcel.chess.service.api.interaction.ConsoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;
    private ConsoleService consoleService;
    private BoardService boardService;
    private PlayerService playerService;
    private MoveService moveService;

    public GameServiceImpl(GameRepository gameRepository, ConsoleService consoleService, BoardService boardService, PlayerService playerService, MoveService moveService) {
        this.gameRepository = gameRepository;
        this.consoleService = consoleService;
        this.boardService = boardService;
        this.playerService = playerService;
        this.moveService = moveService;
    }

    public void start() {
        setupGame();


        gameRepository.setGameStatus(GameStatus.IN_PROGRESS);
        consoleService.println(Message.PLAYER_MOVE_INSTRUCTIONS);


        List<Player> players = gameRepository.getPlayers();
        while (GameStatus.IN_PROGRESS.equals(gameRepository.getGameStatus())) {
            for (Player player : players) {
                if (!GameStatus.IN_PROGRESS.equals(gameRepository.getGameStatus())) {
                    break;
                }
                moveService.requestMove(player);
            }
        }

        displayEndOfGameInfo();

    }

    private void displayEndOfGameInfo() {
        if (!GameStatus.FINISHED.equals(gameRepository.getGameStatus())) {
            return;
        }

        consoleService.println(Message.END_OF_GAME);
        consoleService.println(Message.END_OF_GAME_WINNER + gameRepository.getWinner().getName(), gameRepository.getWinner().getPlayerColor());
        consoleService.println(Message.END_OF_GAME_BOARD);


        boardService.printBoard();
    }

    private void setupGame() {
        consoleService.println(Message.GAME_WELCOME);

        consoleService.println(Message.GAME_INSTRUCTION);
        consoleService.println(Message.GAME_INSTRUCTION_2);
        consoleService.println(Message.GAME_INSTRUCTION_3);

        playerService.createPlayers();
        boardService.createBoard();
        boardService.setBoard();
    }
}