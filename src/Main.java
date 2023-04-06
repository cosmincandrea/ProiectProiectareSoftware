import Presenter.GamePresenter;
import Presenter.LoginPresenter;
import Presenter.MainPresenter;
import models.GameState;
import models.User;
import models.UserType;
import models.repo.UserDao;
import view.GameView;
import view.MainView;

public class Main {
    public static void main(String[] args) {

//        UserDao userDao = new UserDao();
//        User user = new User("Cosminn", "TE",15, "parola");
//        user.setRole(UserType.ADMIN);
//        userDao.updateUser(user);
        //        GameState gameState = new GameState(5);
//        GameView gameView = new GameView(gameState);
//        gameView.drawGame();
//        GamePresenter gamePresenter = new GamePresenter();
//        gamePresenter.init();
//        MainView mainView = new MainView();
//        mainView.init();
        //LoginPresenter loginPresenter = new LoginPresenter();
        MainPresenter mainPresenter = new MainPresenter();
        mainPresenter.start();

    }
}