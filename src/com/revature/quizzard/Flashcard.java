package com.revature.quizzard;

public class Flashcard {

    private String creator;
    private String questionText;
    private String answerText;

    public Flashcard(String creator, String questionText, String answerText) {
        this.creator = creator;
        this.questionText = questionText;
        this.answerText = answerText;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    // Double colon delimiters used in case question of answer text contains a colon
    public String toFileString() {
        return creator + "::" + questionText + "::" + answerText + "\n";
    }

}
