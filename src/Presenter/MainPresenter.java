package Presenter;

import models.User;
import models.UserType;
import view.LoginView;
import view.MainView;

public class MainPresenter {

    private MainView mainView;
    private GamePresenter gamePresenter;
    private LoginPresenter loginPresenter;
    private RegisterPresenter registerPresenter;
    public User currentUser;
    CRUDPresenter crudPresenter;

    public MainPresenter() {
        mainView = new MainView(this);
        gamePresenter = new GamePresenter(this);
        currentUser = new User();
        registerPresenter = new RegisterPresenter(this);
        loginPresenter = new LoginPresenter(currentUser,this);
        crudPresenter = new CRUDPresenter(this);
    }

    public void start(){
        loginPresenter.startLoginIn();
    }
    public void startRegister(){
        loginPresenter.close();
        registerPresenter.beginRegister();
    }

    public void loginSuccessful(User user){
        this.currentUser = user;
        mainView.setGreeting(currentUser);
        if(user.getRole() == UserType.PLAYER){
            mainView.hideCRUDBtn();
        }
        mainView.init();
    }

    public void startCRUDoperations(){
        crudPresenter.start();
    }

    public void registerDone(){
        loginPresenter.startLoginIn();
        registerPresenter.close();
    }

    public void play(int level){
        gamePresenter.init(level);
    }
    public void updateGreeting(){
        mainView.setGreeting(currentUser);
    }
}
