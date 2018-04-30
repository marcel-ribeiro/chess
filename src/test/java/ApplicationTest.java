import com.marcel.chess.service.api.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {
    @Mock
    private GameService gameService;

    @InjectMocks
    private Application application;

    @Test
    public void shouldTriggerGameStart() throws Exception {
        //given
        //when
        application.run();
        //then
        verify(gameService).start();
    }

}