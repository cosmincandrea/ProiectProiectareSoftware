package ViewModel;

import ViewModel.commands.CommandInterface;
import ViewModel.commands.LoginComand;
import models.User;
import models.repo.UserDao;
import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import view.LoginView;

public class LoginVM {

    User currentUser;
    UserDao userDao;
    LoginView loginView;
    MainVM mainVM;

    public LoginVM(User user, MainVM mainVM) {
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
