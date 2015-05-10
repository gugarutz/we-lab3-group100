package controllers;

import at.ac.tuwien.big.we15.lab2.api.impl.PlayJeopardyFactory;
import models.*;
import play.cache.Cache;
import play.data.Form;
import play.db.jpa.JPA;
import play.mvc.*;
import play.api.data.*;
import play.api.data.Forms.*;

import views.html.*;

import javax.persistence.EntityManager;

import at.ac.tuwien.big.we15.lab2.api.*;

import java.util.ArrayList;
import java.util.List;

@Security.Authenticated(Secured.class)
public class SecureArea extends Controller {

    public static Result jeopardy() {
        String username = session("username");
        boolean isRestart = request().getQueryString("restart") != null;
        GameStats gameStats;

        if(Cache.get(username) == null || isRestart) {
            startGame();
        }

        gameStats = (GameStats)Cache.get(username);

        return ok(jeopardy.render(gameStats));
    }

    private static void startGame() {
        String username = session("username");
        String lng = !Controller.lang().code().equals("de") || !Controller.lang().code().equals("en") ? "en" : Controller.lang().code();
        String pathToQuestions = String.format("data.%s.json", lng);
        JeopardyFactory factory = new PlayJeopardyFactory(pathToQuestions);
        JeopardyGame game = factory.createGame(username);

        game.getHuman().setName(username);

        GameStats gameStats = new GameStats(game);

        Cache.remove(username);
        Cache.set(username, gameStats);
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect("login");
    }

    public static Result question() {
        String username = session("username");
        GameStats gameStats = (GameStats)Cache.get(username);

        int questionId = (Form.form(models.ChooseQuestion.class).bindFromRequest()).get().question_selection;
        gameStats.getGame().chooseHumanQuestion(questionId);
        Question q = findQuestion(questionId);

        return ok(question.render(gameStats, q));
    }

    private static Question findQuestion(int questionId) {
        String username = session("username");
        GameStats gameStats = (GameStats)Cache.get(username);
        Question question = null;

        for(Category c : gameStats.getGame().getCategories()) {
            for(Question q : c.getQuestions()) {
                if(q.getId() == questionId) {
                    question = q;
                }
            }
        }

        return question;
    }

    public static Result answer() {
        String username = session("username");
        GameStats gameStats = (GameStats)Cache.get(username);
        Player human = gameStats.getGame().getHumanPlayer();
        Player marvin = gameStats.getGame().getMarvinPlayer();

        List<Integer> answers = (Form.form(ChooseAnswers.class).bindFromRequest()).get().answers;
        answers = answers == null ? new ArrayList<>() : answers;

        gameStats.getGame().answerHumanQuestion(answers);

        Question humanQuestion = human.getAnsweredQuestions().get(human.getAnsweredQuestions().size() - 1);
        Question marvinQuestion = marvin.getAnsweredQuestions().get(marvin.getAnsweredQuestions().size() - 1);

        gameStats.getChosenQuestions().add(humanQuestion.getId());
        gameStats.getChosenQuestions().add(marvinQuestion.getId());

        gameStats.setLastHumanQuestion(humanQuestion, human.getLatestProfitChange());
        gameStats.setLastMarvinQuestion(marvinQuestion, marvin.getLatestProfitChange());

        gameStats.incrementRound();

        if(gameStats.getCurrentRound() == gameStats.getGame().getMaxQuestions()) {
            return redirect("winner");
        }

        return ok(jeopardy.render(gameStats));
    }

    public static Result winner() {
        String username = session("username");
        GameStats gameStats = (GameStats)Cache.get(username);

        return ok(winner.render(gameStats));
    }
}
