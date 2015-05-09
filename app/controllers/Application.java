package controllers;

import models.Login;
import models.Register;
import models.User;
import play.*;
import play.data.Form;
import play.db.jpa.JPA;
import play.mvc.*;
import play.api.data.*;
import play.api.data.Forms.*;

import views.html.*;

import javax.persistence.EntityManager;
import java.util.HashMap;

public class Application extends Controller {

    public static Result playHelp() {
        return ok(index.render("Überschrift lol"));
    }

    public static Result registration() {

        return ok(registration.render(Form.form(User.class)));
    }

    public static Result login() {

        return ok(authentication.render(Form.form(Login.class)));
    }

    @play.db.jpa.Transactional
    public static Result authenticate() {
        Form<Login> loginForm;
        loginForm = Form.form(Login.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(authentication.render(loginForm));
        } else {
            session().clear();
            session("username", loginForm.get().username);
            return redirect("registration");
        }
    }

    @play.db.jpa.Transactional
    public static User getUser(String username, String password) {
        EntityManager em = JPA.em();
        HashMap<String, Object> params = new HashMap<>();
        params.put("password", password);

        if (em.find(User.class, username, params) == null) {
            return null;
        }
        return em.find(User.class, username);
    }

    @play.db.jpa.Transactional
    public static User getUser(String username) {
        EntityManager em = JPA.em();
        if (em.find(User.class, username) == null) {
            return null;
        }
        return em.find(User.class, username);
    }

    @play.db.jpa.Transactional
    public static void saveUser(User user) {
        EntityManager em = JPA.em();
        em.persist(user);
    }
}
