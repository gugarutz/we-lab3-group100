package models;

import controllers.Application;

import java.util.Date;

public class Register {
    private User user;

    public Register() {
        user = new User();
    }

    public String getFirstname() {
        return user.getFirstname();
    }

    public void setFirstname(String firstname) {
        user.setFirstname(firstname);
    }

    public String getLastname() {
        return user.getLastname();
    }

    public void setLastname(String lastname) {
        user.setLastname(lastname);
    }

    public Date getBirthdate() {
        return user.getBirthdate();
    }

    public void setBirthdate(Date birthdate) {
        user.setBirthdate(birthdate);
    }

    public String getAvatar() {
        return user.getAvatar();
    }

    public void setAvatar(String avatar) {
        user.setAvatar(avatar);
    }

    public String getGender() {
        return user.getGender();
    }

    public void setGender(String gender) {
        user.setGender(gender);
    }

    public String getUsername() {
        return user.getUsername();
    }

    public void setUsername(String username) {
        user.setUsername(username);
    }

    public String getPassword() {
        return user.getPassword();
    }

    public void setPassword(String password) {
        user.setPassword(password);
    }

    public String validate() {
        if (Application.getUser(user.getUsername()) != null) {
            return "Username already used!";
        }
        return null;
    }
}
