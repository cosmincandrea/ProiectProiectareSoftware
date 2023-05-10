package models.repo;

import models.User;
import models.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection connection;

    public UserDao() {
        connection = ConnectionFactory.getConnection();
    }

    public User findUserByEmail(String email){

        User returnUser = null;

        String findUserStatement = "Select * from users where email = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(findUserStatement);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (!rs.next()) return null;

            String name = rs.getString("name");
            int score = rs.getInt("score");
            String password = rs.getString("password");
            UserType role = UserType.PLAYER;
            String DBRole = rs.getString("role");
            if (DBRole != null)
                if (DBRole.equals("ADMIN"))
                    role = UserType.ADMIN;
                else
                    role = UserType.PLAYER;
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
        String role = user.getRole().toString();

        String insertStatement = "INSERT INTO users (name,password,email,score, role)"
                + " VALUES (?,?,?,?,?)";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(insertStatement);
            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setInt(4, score);
            stmt.setString(5,role);
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionFactory.close(stmt);
        }

    }

    public int deleteUser(User user){

        String deleteUserStatement = "DELETE FROM users WHERE email = ?";
        PreparedStatement stmt = null;
        int num = 0;
        try {
            stmt = connection.prepareStatement(deleteUserStatement);
            stmt.setString(1, user.getEmail());
            num = stmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(stmt);
        }
        return num;
    }

    public void updateUser(User user){
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        int score = user.getScore();
        String role = user.getRole().toString();

        String insertStatement = "UPDATE users " +
                " SET name = ?, password = ?, score = ?, role = ? " +
                " WHERE email = ?";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(insertStatement);
            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setInt(3, score);
            stmt.setString(4,role);
            stmt.setString(5, email);
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionFactory.close(stmt);
        }
    }

    public List<User> getUsers(){
        List<User> returnUsers = new ArrayList<>();

        String findUsersStatement = "Select * from users";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(findUsersStatement);
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                String name = rs.getString("name");
                int score = rs.getInt("score");
                String email = rs.getString("email");
                String password = rs.getString("password");
                UserType role = UserType.PLAYER;
                String DBRole = rs.getString("role");
            if (DBRole != null)
                if (DBRole.equals("ADMIN"))
                    role = UserType.ADMIN;
                else
                    role = UserType.PLAYER;
                user = new User(name, email, score, password);
                user.setRole(role);
                returnUsers.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(stmt);
            ConnectionFactory.close(rs);
        }
        return returnUsers;
    }

    public boolean userExists(String email, String password){
        User user = findUserByEmail(email);
        if (user == null)
            return false;
        if(!user.getPassword().equals(password)){
            return false;
        }
        return true;
    }

}
