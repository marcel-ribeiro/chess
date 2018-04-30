package com.marcel.chess.service.api;

import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.board.SquareFormatterType;

public interface SquareFormatterService {
    String SYMBOL_SEPARATOR = " ";

    SquareFormatterType getSquareFormatterType();

    String getPieceText(Square square);
}
