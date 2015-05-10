package models;

import at.ac.tuwien.big.we15.lab2.api.Question;

/**
 * Created by Raimund on 10.05.2015.
 */
public class LastQuestion {
    private Question question;
    private boolean isCorrect;
    private int value;

    public LastQuestion(Question question, int value) {
        this.question = question;
        this.isCorrect = isCorrect;
        this.value = value;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isCorrect() {
        return this.value > 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
