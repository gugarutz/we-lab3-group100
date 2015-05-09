package controllers;

import models.Login;
import models.User;
import play.*;
import play.data.Form;
import play.db.jpa.JPA;
import play.mvc.*;
import play.api.data.*;
import play.api.data.Forms.*;

import views.html.*;

import javax.persistence.EntityManager;

@Security.Authenticated(Secured.class)
public class SecureArea extends Controller {

    public static Result jeopardy() {
        return ok(jeopardy.render());
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect("login");
    }
}
