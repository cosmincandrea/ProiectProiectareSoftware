package ViewModel;

import models.User;
import models.repo.UserDao;
import view.RegisterView;

public class RegisterVM {
    private RegisterView registerView;
    private MainVM mainVM;
    private UserDao userDao;
    public RegisterVM(MainVM mainVM) {
        this.mainVM = mainVM;
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
        mainVM.registerDone();
    }

}
