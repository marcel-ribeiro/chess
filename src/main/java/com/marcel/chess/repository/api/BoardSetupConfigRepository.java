package com.marcel.chess.repository.api;

import com.google.common.collect.ListMultimap;
import com.marcel.chess.model.board.SquareSetupConfig;

/**
 * <p>Repository that holds the initial configuration of the board</p>
 */
public interface BoardSetupConfigRepository {
    /**
     * <p>Provides a ListMultimap object with the information required to setup the board</p>
     *
     * @return boardSetupConfigMap
     */
    ListMultimap<Integer, SquareSetupConfig> getBoardSetupConfigMap();
}
