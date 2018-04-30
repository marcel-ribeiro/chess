package com.marcel.chess.service.api;

import com.marcel.chess.model.player.Player;

public interface PlayerService {
    void createPlayers();

    void updatePlayerScore(Player player);
}
