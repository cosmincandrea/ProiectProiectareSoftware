package Controller;

import models.User;
import models.repo.UserDao;
import view.LoginView;

public class LoginController {

    User currentUser;
    UserDao userDao;
    LoginView loginView;
    MainController mainVM;

    public LoginController(User user, MainController mainVM) {
        this.currentUser = user;
        this.mainVM = mainVM;
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
            mainVM.loginSuccessful(user);
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
        mainVM.startRegister();
    }

}
