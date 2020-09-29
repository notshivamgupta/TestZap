package com.example.testzap;

public class Questionmodel {
    String Question,Option0,Option2,Option3,Option1;
    public Questionmodel() {
    }

    public Questionmodel(String question, String option0, String option2, String option3, String option1) {
        Question = question;
        Option0 = option0;
        Option2 = option2;
        Option3 = option3;
        Option1 = option1;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getOption0() {
        return Option0;
    }

    public void setOption0(String option0) {
        Option0 = option0;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }
}
