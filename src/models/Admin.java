package models;

public class Admin extends User{


    public Admin() {
    }

    public Admin(String name, String email, Integer score, String username, String password) {
        super(name, email, score, username, password);
    }

    public Admin(String name, String email, String username, String password) {
        super(name, email, username, password);
    }
}
