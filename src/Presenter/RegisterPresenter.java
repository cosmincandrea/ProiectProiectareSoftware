package Presenter;

import models.User;
import models.repo.UserDao;
import view.RegisterView;

public class RegisterPresenter {
    private RegisterView registerView;
    private MainPresenter mainPresenter;
    private UserDao userDao;
    public RegisterPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        registerView = new RegisterView(this);
        userDao = new UserDao();
    }
    public void beginRegister(){
        registerView.init();
    }
    public boolean checkEmail(String email){
        if (userDao.findUserByEmail(email) != null){
            return false;
        }
        return true;
    }

    public void close(){
        registerView.close();
    }
    public void addUser(){
        User user = new User();
        user.setRole(registerView.getRole());
        user.setName(registerView.getName());
        user.setEmail(registerView.getEmail());
        user.setPassword(registerView.getPassword());
        user.setScore(0);
        userDao.insertUser(user);
        mainPresenter.registerDone();
    }

}
