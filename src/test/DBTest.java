package test;

import junit.framework.Assert;
import models.User;
import models.repo.ConnectionFactory;
import models.repo.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import static junit.framework.TestCase.*;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBTest {
    Connection connectionFactory;
    UserDao userDao;
    User user1;
    User user2;
    User user3;
    @Before
    public void setup(){
        connectionFactory = ConnectionFactory.getConnection();
        userDao = new UserDao();
        user1 = new User("Cosmin1", "emial1", 45, "pass1");
        user2 = new User("No", "No", 44, "no");
        user3 = new User();
    }
    @Test
    public void testConection(){
            Assert.assertNotNull(connectionFactory);
    }
    @Test
    public void testInsert(){
        userDao.insertUser(user1);
        assertEquals(user1, userDao.findUserByEmail(user1.getEmail()));
        userDao.deleteUser(user1);
    }
    @Test
    public void testUpdate(){
        userDao.insertUser(user1);
        user1.setName("NEW NAME");
        userDao.updateUser(user1);
        assertEquals(user1, userDao.findUserByEmail(user1.getEmail()));
        userDao.deleteUser(user1);
    }
    @Test
    public void testNULLDelete(){
       int deletedUsers = userDao.deleteUser(user3);
       assertEquals(0, deletedUsers);
    }
    @After
    public void closeTest(){
        try {
            connectionFactory.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
