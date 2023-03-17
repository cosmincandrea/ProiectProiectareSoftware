package models;

public class User {

    private String name;
    private String email;
    private Integer score;
    private String username;
    private String password;

    public User() {
        this.name = "";
        this.email = "";
        this.score = 0;
        this.username = "";
        this.password = "";
    }

    public User(String name, String email, Integer score, String username, String password) {
        this.name = name;
        this.email = email;
        this.score = score;
        this.username = username;
        this.password = password;
    }

    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return this.name + " -> " + this.score;
    }

}
