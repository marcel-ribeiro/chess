package com.marcel.chess.service.impl.validator.move;

import com.google.common.collect.Lists;
import com.marcel.chess.model.board.Square;
import com.marcel.chess.model.pieces.Piece;
import com.marcel.chess.model.pieces.PieceType;
import com.marcel.chess.model.player.Player;
import com.marcel.chess.model.validator.MoveType;
import com.marcel.chess.service.api.BoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MoveValidationUtilImplTest {

    @Mock
    private BoardService boardService;

    @InjectMocks
    private MoveValidationUtilImpl moveValidationUtil;

    private Square square11 = new Square(1, 1);
    private Square square14 = new Square(1, 4);
    private Square square41 = new Square(4, 1);
    private Square square44 = new Square(4, 4);
    private Square square32 = new Square(3, 2);
    private Square square23 = new Square(2, 3);

    @Test
    public void shouldReturnHorizontal() throws Exception {
        //given
        //when
        MoveType moveType = moveValidationUtil.getMoveType(square11, square14);
        //then
        assertThat(moveType).isEqualTo(MoveType.HORIZONTAL);
    }

    @Test
    public void shouldReturnVertical() throws Exception {
        //given
        //when
        MoveType moveType = moveValidationUtil.getMoveType(square11, square41);
        //then
        assertThat(moveType).isEqualTo(MoveType.VERTICAL);
    }

    @Test
    public void shouldReturnDiagonal() throws Exception {
        //given
        //when
        MoveType moveType = moveValidationUtil.getMoveType(square11, square44);
        //then
        assertThat(moveType).isEqualTo(MoveType.DIAGONAL);
    }

    @Test
    public void shouldReturnLShapeWhenTheLIsVertical() throws Exception {
        //given
        //when
        MoveType moveType = moveValidationUtil.getMoveType(square11, square32);
        //then
        assertThat(moveType).isEqualTo(MoveType.L_SHAPE);
    }

    @Test
    public void shouldReturnLShapeWhenTheLIsHorizontal() throws Exception {
        //given
        //when
        MoveType moveType = moveValidationUtil.getMoveType(square11, square23);
        //then
        assertThat(moveType).isEqualTo(MoveType.L_SHAPE);
    }

    @Test
    public void shoulNotdReturnLShape() throws Exception {
        //given
        Square square22 = new Square(2, 2);
        Square square33 = new Square(3, 3);

        //when
        MoveType moveType = moveValidationUtil.getMoveType(square22, square33);
        //then
        assertThat(moveType).isNotEqualTo(MoveType.L_SHAPE);
    }

    @Test
    public void shouldIReturnTrueWhenThereIsAPieceInTheWayHorizontal() throws Exception {
        //given
        Square emptySquare12 = new Square(1, 2);
        Square squareInTheWay = new Square(1, 3);
        Piece pieceInTheWay = new Piece(PieceType.PAWN, mock(Player.class));
        squareInTheWay.setPiece(pieceInTheWay);
        setGetSquareMocks(Lists.newArrayList(emptySquare12, squareInTheWay));
        //when
        boolean somethingInTheWay = moveValidationUtil.isSomethingInTheWay(square11, square14);
        //then
        assertThat(somethingInTheWay).isTrue();
    }


    @Test
    public void shouldIReturnTrueWhenThereIsAPieceInTheWayVertical() throws Exception {
        //given
        Square emptySquare21 = new Square(2, 1);
        Square squareInTheWay = new Square(3, 1);
        Piece pieceInTheWay = new Piece(PieceType.PAWN, mock(Player.class));
        squareInTheWay.setPiece(pieceInTheWay);
        setGetSquareMocks(Lists.newArrayList(emptySquare21, squareInTheWay));
        //when
        boolean somethingInTheWay = moveValidationUtil.isSomethingInTheWay(square11, square41);
        //then
        assertThat(somethingInTheWay).isTrue();
    }

    @Test
    public void shouldIReturnTrueWhenThereIsAPieceInTheWayDiagonal() throws Exception {
        //given
        Square emptySquare22 = new Square(2, 2);
        Square squareInTheWay = new Square(3, 3);
        Piece pieceInTheWay = new Piece(PieceType.PAWN, mock(Player.class));
        squareInTheWay.setPiece(pieceInTheWay);
        setGetSquareMocks(Lists.newArrayList(emptySquare22, squareInTheWay));
        //when
        boolean somethingInTheWay = moveValidationUtil.isSomethingInTheWay(square11, square44);
        //then
        assertThat(somethingInTheWay).isTrue();
    }

    private void setGetSquareMocks(List<Square> squares) {
        for (Square square : squares) {
            when(boardService.getSquare(square.getRow(), square.getColumn())).thenReturn(square);
        }
    }

    @Test
    public void shouldReturnThreeForVerticalMovesLowerToGreater() throws Exception {
        //given
        //when
        Integer numberOfSquaresMoved = moveValidationUtil.getNumberOfSquaresMoved(square11, square41);
        //then
        assertThat(numberOfSquaresMoved).isEqualTo(3);
    }

    @Test
    public void shouldReturnThreeForVerticalMovesGreaterToLower() throws Exception {
        //given
        //when
        Integer numberOfSquaresMoved = moveValidationUtil.getNumberOfSquaresMoved(square41, square11);
        //then
        assertThat(numberOfSquaresMoved).isEqualTo(3);
    }
}