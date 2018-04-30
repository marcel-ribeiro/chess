package com.marcel.chess.model.constants;

import com.marcel.chess.service.api.interaction.Color;

/**
 * <p>Interface that holds some constant values used to configure and run the game</p>
 */
public interface GameConfig {
    int BOARD_ROWS = 8;
    int BOARD_COLUMNS = 8;
    int NUMBER_OF_PLAYERS = 2;
    char SQUARE_DEFAULT_SYMBOL = 'x';
    int GAME_WINNER_SCORE = 100;
    int PAWNS_ROW_RED = 1;
    int PAWNS_ROW_GREEN = 6;
    int INVALID_NUMBER_OF_SQUARES_MOVED = Integer.MAX_VALUE;

    Color[] playerColorMap = new Color[]{Color.RED, Color.GREEN};
}
