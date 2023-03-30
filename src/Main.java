import models.GameState;
import models.User;
import models.repo.UserDao;
import view.GameView;

public class Main {
    public static void main(String[] args) {

        /*UserDao userDao = new UserDao();
        User user = new User("Cosmin Candrea", "cosmin*candrea", 12,"parola");
        userDao.insertUser(user);
        */
        GameState gameState = new GameState(5);
        GameView gameView = new GameView(gameState);
        gameView.drawGame();
    }
}