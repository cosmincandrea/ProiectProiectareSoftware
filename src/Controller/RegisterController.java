package Controller;

import models.User;
import models.repo.UserDao;
import view.RegisterView;

public class RegisterController {
    private RegisterView registerView;
    private MainController mainController;
    private UserDao userDao;
    public RegisterController(MainController mainVM) {
        this.mainController = mainVM;
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
        mainController.registerDone();
    }

}
