package models;

import at.ac.tuwien.big.we15.lab2.api.JeopardyGame;
import at.ac.tuwien.big.we15.lab2.api.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raimund on 10.05.2015.
 */
public class GameStats {
    private int currentRound;
    private JeopardyGame game;
    private List<Integer> chosenQuestions;

    private LastQuestion lastHumanQuestion;
    private LastQuestion lastMarvinQuestion;

    public GameStats(JeopardyGame game) {
        this.game = game;
        this.currentRound = 0;
        this.chosenQuestions = new ArrayList<>();
    }

    public String getDisabledAttribute(int id) {
        return this.chosenQuestions.contains(id) ? "disabled=\"disabled\"" : "";
    }

    public List<Integer> getChosenQuestions() {
        return this.chosenQuestions;
    }

    public LastQuestion getLastHumanQuestion() {
        return lastHumanQuestion;
    }

    public void setLastHumanQuestion(Question question, int value) {
        this.lastHumanQuestion = new LastQuestion(question, value);
    }

    public LastQuestion getLastMarvinQuestion() {
        return lastMarvinQuestion;
    }

    public void setLastMarvinQuestion(Question question, int value) {
        this.lastMarvinQuestion = new LastQuestion(question, value);
    }

    public int getCurrentRound() {
        return this.currentRound;
    }

    public void incrementRound() {
        this.currentRound ++;
    }

    public JeopardyGame getGame() {
        return this.game;
    }
}
