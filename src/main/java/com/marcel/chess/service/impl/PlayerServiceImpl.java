package com.marcel.chess.service.impl;

import com.google.common.collect.Lists;
import com.marcel.chess.model.constants.GameConfig;
import com.marcel.chess.model.constants.Message;
import com.marcel.chess.model.game.GameStatus;
import com.marcel.chess.model.player.Player;
import com.marcel.chess.repository.api.GameRepository;
import com.marcel.chess.service.api.PlayerService;
import com.marcel.chess.service.api.interaction.Color;
import com.marcel.chess.service.api.interaction.ConsoleService;
import com.marcel.chess.service.api.validator.input.InputValidatorType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private ConsoleService consoleService;
    private GameRepository gameRepository;

    public PlayerServiceImpl(ConsoleService consoleService, GameRepository gameRepository) {
        this.consoleService = consoleService;
        this.gameRepository = gameRepository;
    }

    @Override
    public void createPlayers() {
        List<Player> players = Lists.newArrayList();
        consoleService.println(Message.GAME_CREATING_PLAYERS);

        for (int playerNumber = 0; playerNumber < GameConfig.NUMBER_OF_PLAYERS; playerNumber++) {
            createPlayer(players, GameConfig.playerColorMap[playerNumber]);
        }
        gameRepository.setPlayers(players);
    }

    private void createPlayer(List<Player> players, Color playerColor) {
        String playerName = consoleService.requestInput(Message.GAME_PLAYER_NAME.getText(), Color.DEFAULT, InputValidatorType.DEFAULT);
        Player player = new Player(playerName, playerColor);
        players.add(player);

        String gamePlayerCreated = Message.GAME_PLAYER_CREATED + player.toString();
        consoleService.println(gamePlayerCreated, playerColor);
    }

    @Override
    public void updatePlayerScore(Player player) {
        if (player.getScore() >= GameConfig.GAME_WINNER_SCORE) {
            gameRepository.setGameStatus(GameStatus.FINISHED);
            gameRepository.setWinner(player);
        }

    }
}
