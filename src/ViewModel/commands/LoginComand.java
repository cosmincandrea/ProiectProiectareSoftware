package ViewModel.commands;

import ViewModel.LoginVM;
import ViewModel.MainVM;
import ViewModel.RegisterVM;
import models.User;
import models.repo.UserDao;
import view.MainView;
import view.RegisterView;

public class LoginComand implements CommandInterface{
    private LoginVM loginVM;
    User user;
    public LoginComand(LoginVM loginVM){
        this.loginVM = loginVM;
    }

    @Override
    public void execute() {
        UserDao userDao = new UserDao();

        String username = loginVM.getUsername();
        String password = loginVM.getPassword();

        if (username.equals("") && password.equals(""))
        {
            RegisterVM registerVM = new RegisterVM(null);
            RegisterView registerView = new RegisterView(registerVM);
        }

        if (userDao.userExists(username, password)) {
            user = userDao.findUserByEmail(username);
            MainVM mainVM = new MainVM();
            mainVM.currentUser = user;
            MainView mainView = new MainView(mainVM);
        }

    }
}
