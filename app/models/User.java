package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

import controllers.Application;
import play.data.validation.Constraints.Required;

@Entity
public class User {

    private String firstname;
    private String lastname;
    private Date birthdate;
    private String gender;
    private String avatar;

    @Id
    @Required
    private String username;

    @Required
    private String password;

    public User() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String validate() {
        if (Application.getUser(getUsername()) != null) {
            return "Username already used!";
        }
        return null;
    }
}