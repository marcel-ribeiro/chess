import com.marcel.chess.service.api.GameService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.marcel.chess"})
@SpringBootApplication
public class Application implements CommandLineRunner {

    private GameService gameService;

    public Application(GameService gameService) {
        this.gameService = gameService;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        gameService.start();
    }
}