package com.example.testzap;

public class HistoryModel {

    String sub_name, sub_set;
    int correct, incorrect,  time_taken;

    public HistoryModel() {
    }

    public HistoryModel(String sub_name, String sub_set, int correct, int incorrect, int time_taken) {
        this.sub_name = sub_name;
        this.sub_set = sub_set;
        this.correct = correct;
        this.incorrect = incorrect;
        this.time_taken = time_taken;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getSub_set() {
        return sub_set;
    }

    public void setSub_set(String sub_set) {
        this.sub_set = sub_set;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }

    public int getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(int time_taken) {
        this.time_taken = time_taken;
    }
}
