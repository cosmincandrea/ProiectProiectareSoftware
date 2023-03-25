import models.User;
import models.repo.UserDao;

public class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        User user = new User("Cosmin Candrea", "cosmin*candrea", 12,"parola");
        userDao.insertUser(user);

    }
}