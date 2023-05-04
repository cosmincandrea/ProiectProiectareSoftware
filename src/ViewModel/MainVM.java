package ViewModel;

import models.User;
import models.UserType;
import view.MainView;

public class MainVM {

    private MainView mainView;
    private GameVM gameVM;
    private LoginVM loginVM;
    private RegisterVM registerVM;
    public User currentUser;
    CRUDVM CRUDVM;

    public MainVM() {
        mainView = new MainView(this);
        gameVM = new GameVM(this);
        currentUser = new User();
        registerVM = new RegisterVM(this);
        loginVM = new LoginVM(currentUser,this);
        CRUDVM = new CRUDVM(this);
    }

    public void start(){
        loginVM.startLoginIn();
    }
    public void startRegister(){
        loginVM.close();
        registerVM.beginRegister();
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
        CRUDVM.start();
    }

    public void registerDone(){
        loginVM.startLoginIn();
        registerVM.close();
    }

    public void play(int level){
        gameVM.init(level);
    }
    public void updateGreeting(){
        mainView.setGreeting(currentUser);
    }
}
