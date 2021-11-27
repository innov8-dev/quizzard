package com.revature.quizzard.models;

public class Flashcard {

    private String id;
    private String creator;
    private String questionText;
    private String answerText;

    public Flashcard(String questionText, String answerText) {
        this.questionText = questionText;
        this.answerText = answerText;
    }

    public Flashcard(String creator, String questionText, String answerText) {
        this(questionText, answerText);
        this.creator = creator;
    }

    public Flashcard(String id, String creator, String questionText, String answerText) {
        this(creator, questionText, answerText);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return id + "::" + creator + "::" + questionText + "::" + answerText + "\n";
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "id='" + id + '\'' +
                ", creator='" + creator + '\'' +
                ", questionText='" + questionText + '\'' +
                ", answerText='" + answerText + '\'' +
                '}';
    }

}
