package models.repo;

import models.User;
import models.UserType;

import java.sql.*;

public class UserDao {
    Connection connection;

    public UserDao() {
        connection = ConnectionFactory.getConnection();
    }

    public User findUserByEmail(String email){

        User returnUser = null;

        String findUserStatement = "Select * from user where email = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(findUserStatement);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            String name = rs.getString("name");
            int score = rs.getInt("score");
            String password = rs.getString("password");
            UserType role;
            if (rs.getString("role").equals("PLAYER"))
                role = UserType.PLAYER;
            else
                role = UserType.ADMIN;
            returnUser = new User(name, email, score, password);
            returnUser.setRole(role);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(stmt);
            ConnectionFactory.close(rs);
        }
        return returnUser;
    }

    public void insertUser(User user){
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        int score = user.getScore();
        //String role = user.getRole().toString();

        String insertStatement = "INSERT INTO users (name,password,email,score)"
                + " VALUES (?,?,?,?)";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(insertStatement);
            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setInt(4, score);
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionFactory.close(stmt);
        }

    }

}
