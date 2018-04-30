package com.marcel.chess.repository.impl;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import com.marcel.chess.model.board.SquareSetupConfig;
import com.marcel.chess.model.constants.GameConfig;
import com.marcel.chess.model.pieces.PieceType;
import com.marcel.chess.repository.api.BoardSetupConfigRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BoardSetupConfigRepositoryImpl implements BoardSetupConfigRepository {
    private ListMultimap<Integer, SquareSetupConfig> boardSetupConfigMap;

    /**
     * <p>Constructor</p>
     */
    public BoardSetupConfigRepositoryImpl() {
        this.boardSetupConfigMap =
                MultimapBuilder.treeKeys().arrayListValues().build();

        addRoyaltyRow(0, 0);
        addPawnsRow(0, 1);
        addPawnsRow(1, 6);
        addRoyaltyRow(1, 7);
    }

    /**
     * <p>Setuo the row of pices like King, Queen, Rooks, Knights, Bishops....</p>
     *
     * @param playerIndex
     * @param row
     */
    private void addRoyaltyRow(int playerIndex, int row) {
        this.boardSetupConfigMap.put(playerIndex, new SquareSetupConfig(PieceType.ROOK, row, 0));
        this.boardSetupConfigMap.put(playerIndex, new SquareSetupConfig(PieceType.KNIGHT, row, 1));
        this.boardSetupConfigMap.put(playerIndex, new SquareSetupConfig(PieceType.BISHOP, row, 2));
        this.boardSetupConfigMap.put(playerIndex, new SquareSetupConfig(PieceType.QUEEN, row, 3));
        this.boardSetupConfigMap.put(playerIndex, new SquareSetupConfig(PieceType.KING, row, 4));
        this.boardSetupConfigMap.put(playerIndex, new SquareSetupConfig(PieceType.BISHOP, row, 5));
        this.boardSetupConfigMap.put(playerIndex, new SquareSetupConfig(PieceType.KNIGHT, row, 6));
        this.boardSetupConfigMap.put(playerIndex, new SquareSetupConfig(PieceType.ROOK, row, 7));
    }

    /**
     * <p>Setup the pawns row</p>
     *
     * @param playerIndex
     * @param row
     */
    private void addPawnsRow(int playerIndex, int row) {
        for (int column = 0; column < GameConfig.BOARD_COLUMNS; column++) {
            this.boardSetupConfigMap.put(playerIndex, new SquareSetupConfig(PieceType.PAWN, row, column));
        }
    }

    public ListMultimap<Integer, SquareSetupConfig> getBoardSetupConfigMap() {
        return boardSetupConfigMap;
    }
}
