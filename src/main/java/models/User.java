package models;

public class User {

    String email;
    String password;
    String pid;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String pid) {
        this.email = email;
        this.password = password;
        this.pid = pid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPid() {
        return pid;
    }

}