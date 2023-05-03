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
        MainPresenter mainPresenter = new MainPresenter();
        mainPresenter.start();

    }
}