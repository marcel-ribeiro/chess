package com.marcel.chess.service.impl;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.marcel.chess.model.board.Board;
import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.board.SquareFormatterType;
import com.marcel.chess.model.board.SquareSetupConfig;
import com.marcel.chess.model.constants.GameConfig;
import com.marcel.chess.model.pieces.Piece;
import com.marcel.chess.model.player.Player;
import com.marcel.chess.repository.api.BoardSetupConfigRepository;
import com.marcel.chess.repository.api.GameRepository;
import com.marcel.chess.service.api.BoardService;
import com.marcel.chess.service.api.SquareFormatterService;
import com.marcel.chess.service.api.interaction.ConsoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    private static final String SEPARATOR_SPACE = " ";
    private ConsoleService consoleService;
    private GameRepository gameRepository;
    private BoardSetupConfigRepository boardSetupConfigRepository;
    private Map<SquareFormatterType, SquareFormatterService> squareFormatterServices;

    public BoardServiceImpl(ConsoleService consoleService, GameRepository gameRepository, BoardSetupConfigRepository boardSetupConfigRepository, List<SquareFormatterService> squareFormatterServices) {
        this.consoleService = consoleService;
        this.gameRepository = gameRepository;
        this.boardSetupConfigRepository = boardSetupConfigRepository;

        this.squareFormatterServices = Maps.newHashMap();
        for (SquareFormatterService squareFormatterService : squareFormatterServices) {
            this.squareFormatterServices.put(squareFormatterService.getSquareFormatterType(), squareFormatterService);
        }
    }

    @Override
    public void createBoard() {
        ListMultimap<Integer, Square> squares =
                MultimapBuilder.treeKeys().arrayListValues().build();

        for (int column = 0; column < GameConfig.BOARD_COLUMNS; column++) {
            for (int row = 0; row < GameConfig.BOARD_ROWS; row++) {
                squares.put(row, new Square(row, column));
            }
        }

        gameRepository.setBoard(new Board(squares));
    }

    @Override
    public void setBoard() {
        ListMultimap<Integer, SquareSetupConfig> boardSetupConfigMap = boardSetupConfigRepository.getBoardSetupConfigMap();

        List<Player> players = gameRepository.getPlayers();
        for (Map.Entry<Integer, SquareSetupConfig> squareSetupConfigEntry : boardSetupConfigMap.entries()) {
            SquareSetupConfig squareSetupConfig = squareSetupConfigEntry.getValue();
            Player player = players.get(squareSetupConfigEntry.getKey());
            Square square = this.getSquare(squareSetupConfig.getRow(), squareSetupConfig.getColumn());
            square.setPiece(new Piece(squareSetupConfig.getPieceType(), player));
        }

    }

    @Override
    public Square getSquare(Integer row, Integer column) {
        if (GameConfig.BOARD_ROWS <= row || GameConfig.BOARD_COLUMNS <= column) {
            return null;
        }

        Board board = gameRepository.getBoard();
        ListMultimap<Integer, Square> squares = board.getSquares();
        return squares.get(row).get(column);
    }

    @Override
    public void printBoard() {
        this.printBoardShape(SquareFormatterType.SYMBOLS);
    }

    @Override
    public void printBoardWithoutPieces() {
        this.printBoardShape(SquareFormatterType.INDEX);
    }

    @Override
    public void printBoardAsList() {
        Board board = gameRepository.getBoard();
        ListMultimap<Integer, Square> squares = board.getSquares();
        for (Map.Entry<Integer, Square> integerSquareEntry : squares.entries()) {
            consoleService.println(this.getSquareTextFormatted(integerSquareEntry.getValue(), SquareFormatterType.FULL_OBJECT));
        }
    }

    private void printBoardShape(SquareFormatterType squareFormatterType) {
        Board board = gameRepository.getBoard();
        ListMultimap<Integer, Square> squares = board.getSquares();

        printColumnGuide(squares.keySet().size());

        int rowDisplayed = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Square> integerSquareEntry : squares.entries()) {
            Square square = integerSquareEntry.getValue();
            rowDisplayed = printRowGuide(rowDisplayed, square.getRow());
            consoleService.print(this.getSquareTextFormatted(square, squareFormatterType));
        }
        printColumnGuide(squares.keySet().size());
    }

    private int printRowGuide(int rowDisplayed, int squareRow) {
        if (rowDisplayed != squareRow) {

            consoleService.println("");
            rowDisplayed = squareRow;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SEPARATOR_SPACE).append(rowDisplayed + 1).append(SEPARATOR_SPACE);
            consoleService.print(stringBuilder.toString());
        }
        return rowDisplayed;
    }

    private void printColumnGuide(int numberOfColumns) {
        consoleService.println("\n");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SEPARATOR_SPACE).append(SEPARATOR_SPACE).append(SEPARATOR_SPACE);
        for (int column = 1; column <= numberOfColumns; column++) {
            stringBuilder.append(SEPARATOR_SPACE).append(column).append(SEPARATOR_SPACE);
        }

        consoleService.println(stringBuilder.toString());


    }

    private String getSquareTextFormatted(Square square, SquareFormatterType squareFormatterType) {
        SquareFormatterService squareFormatterService = squareFormatterServices.get(squareFormatterType);
        if (ObjectUtils.isEmpty(squareFormatterService)) {
            return null;
        }
        return squareFormatterService.getPieceText(square);
    }
}
