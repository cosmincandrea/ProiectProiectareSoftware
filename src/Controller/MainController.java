package Controller;

import models.User;
import models.UserType;
import view.MainView;

public class MainController {

    private MainView mainView;
    public String lang;
    private GameController gameController;
    private LoginController loginController;
    private RegisterController registerController;
    public User currentUser;
    CRUDController CRUDController;

    public MainController() {
        mainView = new MainView(this);
        gameController = new GameController(this);
        currentUser = new User();
        registerController = new RegisterController(this);
        loginController = new LoginController(currentUser,this);
        CRUDController = new CRUDController(this);
    }

    public void start(){
        loginController.startLoginIn();
    }
    public void startRegister(){
        loginController.close();
        registerController.beginRegister();
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
        CRUDController.start();
    }

    public void registerDone(){
        loginController.startLoginIn();
        registerController.close();
    }

    public void play(int level){
        gameController.init(level, lang);
    }
    public void updateGreeting(){
        mainView.setGreeting(currentUser);
    }
}
