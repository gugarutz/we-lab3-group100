package models;

import controllers.Application;
import views.html.authentication;

public class Login {

    public String username;
    public String password;

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

    public String validate() {
        if (Application.getUser(username, password) == null) {
            return "Invalid user or password";
        }
        return null;
    }
}