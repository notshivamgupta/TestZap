package com.example.testzap;

public class Setsmodel {
    String Name;
    Setsmodel()
    {
    }

    public Setsmodel(String set) {
        this.Name = set;
    }

    public String getSet() {
        return Name;
    }
    public void setSet(String set) {
        this.Name = set;
    }
}
