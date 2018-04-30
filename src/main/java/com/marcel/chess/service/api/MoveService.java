package com.marcel.chess.service.api;

import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.player.Player;

public interface MoveService {
    void requestMove(Player player);

    void performMove(Square squareFrom, Square squareTo) throws Exception;
}
