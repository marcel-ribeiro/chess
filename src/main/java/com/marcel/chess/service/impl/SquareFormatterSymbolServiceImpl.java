package com.marcel.chess.service.impl;

import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.board.SquareFormatterType;
import com.marcel.chess.model.constants.GameConfig;
import com.marcel.chess.model.pieces.Piece;
import com.marcel.chess.model.player.Player;
import com.marcel.chess.service.api.SquareFormatterService;
import com.marcel.chess.service.api.interaction.Color;
import com.marcel.chess.service.impl.interaction.ColorUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class SquareFormatterSymbolServiceImpl implements SquareFormatterService {

    @Override
    public SquareFormatterType getSquareFormatterType() {
        return SquareFormatterType.SYMBOLS;
    }

    @Override
    public String getPieceText(Square square) {
        Piece piece = square.getPiece();
        Character squareSymbol = GameConfig.SQUARE_DEFAULT_SYMBOL;
        String coloredText = ColorUtil.getColoredText(squareSymbol.toString(), Color.DEFAULT);

        StringBuilder stringBuilder = new StringBuilder();
        if (!ObjectUtils.isEmpty(piece)) {
            coloredText = getColoredTextForPiece(piece);
        }

        stringBuilder.append(SYMBOL_SEPARATOR).append(coloredText).append(SYMBOL_SEPARATOR);


        return stringBuilder.toString();
    }

    private String getColoredTextForPiece(Piece piece) {
        Character squareSymbol;
        String coloredText;
        squareSymbol = piece.getSymbol();
        Player player = piece.getPlayer();
        Color playerColor = player.getPlayerColor();
        coloredText = ColorUtil.getColoredText(squareSymbol.toString(), playerColor);
        return coloredText;
    }
}
