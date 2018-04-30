package com.marcel.chess.service.impl;

import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.board.SquareFormatterType;
import com.marcel.chess.service.api.SquareFormatterService;
import org.springframework.stereotype.Service;

@Service
public class SquareFormatterIndexServiceImpl implements SquareFormatterService {
    @Override
    public SquareFormatterType getSquareFormatterType() {
        return SquareFormatterType.INDEX;
    }

    @Override
    public String getPieceText(Square square) {
        return "[" + square.getRow() + square.getColumn() + "]";
    }
}
