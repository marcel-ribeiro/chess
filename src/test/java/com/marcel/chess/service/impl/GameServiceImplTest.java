package com.marcel.chess.service.impl;

import com.marcel.chess.repository.api.GameRepository;
import com.marcel.chess.service.api.BoardService;
import com.marcel.chess.service.api.MoveService;
import com.marcel.chess.service.api.PlayerService;
import com.marcel.chess.service.api.interaction.ConsoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {
    @Mock
    private GameRepository gameRepository;
    @Mock
    private ConsoleService consoleService;
    @Mock
    private BoardService boardService;
    @Mock
    private PlayerService playerService;
    @Mock
    private MoveService moveService;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    public void shouldCreatePlayers() throws Exception {
        //given
        //when
        gameService.start();
        //then
        verify(playerService).createPlayers();
    }

    @Test
    public void shouldCreateBoard() throws Exception {
        //given
        //when
        gameService.start();
        //then
        verify(boardService).createBoard();
    }

}