package com.example.testzap;

public class Questionmodel {

    String question ,correct_answer,option_3,option_2,option_1;
    public Questionmodel() {
    }

    public Questionmodel(String question, String correct_answer, String option_3, String option_2, String option_1) {
        this.question = question;
        this.correct_answer = correct_answer;
        this.option_3 = option_3;
        this.option_2 = option_2;
        this.option_1 = option_1;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getOption_3() {
        return option_3;
    }

    public void setOption_3(String option_3) {
        this.option_3 = option_3;
    }

    public String getOption_2() {
        return option_2;
    }

    public void setOption_2(String option_2) {
        this.option_2 = option_2;
    }

    public String getOption_1() {
        return option_1;
    }

    public void setOption_1(String option_1) {
        this.option_1 = option_1;
    }
}

