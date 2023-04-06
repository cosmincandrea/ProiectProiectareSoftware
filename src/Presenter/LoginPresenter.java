package Presenter;

import models.User;
import models.repo.UserDao;
import view.LoginView;

public class LoginPresenter {

    User currentUser;
    UserDao userDao;
    LoginView loginView;
    MainPresenter mainPresenter;

    public LoginPresenter(User user, MainPresenter mainPresenter) {
        this.currentUser = user;
        this.mainPresenter = mainPresenter;
        this.userDao = new UserDao();
        loginView = new LoginView(this);
    }

    public void logIn(){
        String email = loginView.getEmail();
        String password = loginView.getPassword();
        System.out.println(password);
        User user;
        if (userDao.userExists(email, password)){
            user = userDao.findUserByEmail(email);
            loginView.close();
            mainPresenter.loginSuccessful(user);
        }else{
            System.out.println("NU esxista user");
        }

    }
    public void startLoginIn(){
        loginView.init();
    }
    public void close()
    {
        loginView.close();
    }
    public void  register(){
        mainPresenter.startRegister();
    }

}
