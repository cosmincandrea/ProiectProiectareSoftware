package Presenter;

import models.User;
import models.repo.UserDao;
import view.CRUDview;

import java.util.List;

public class CRUDPresenter {
    private MainPresenter mainPresenter;
    private CRUDview crudView;
    private UserDao userDao;

    public CRUDPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        this.crudView = new CRUDview(this);
        this.userDao = new UserDao();
    }

    public void setData(){
        List<User> users = userDao.getUsers();
        String[][] data = new String[users.toArray().length][5];
        int index = 0;
        for (User user: users){
            data[index][0] = user.getName();
            data[index][1] = user.getEmail();
            data[index][2] = user.getPassword();
            data[index][3] = String.valueOf(user.getScore());
            data[index][4] = user.getRole().toString();
            index++;
        }
        String[] cols = new String[5];
        cols[0] = "Name";
        cols[1] = "Email";
        cols[2] = "Password";
        cols[3] = "Score";
        cols[4] = "Role";
        crudView.setTableColumns(data, cols);
    }

    public void updateUser(User user){
        userDao.updateUser(user);
        refresh();
    }

    public void insertUser(User user){
        userDao.insertUser(user);
        refresh();
    }

    public void deleteUser(User user){
        userDao.deleteUser(user);
        refresh();
    }
    public void refresh(){
        setData();
    }

    public void start(){
        crudView.init();
        setData();
    }

}
