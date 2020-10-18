package com.example.testzap;

public class AddChatModel {
    String Email_Id,Full_Name,status;

    public AddChatModel() {
    }

    public AddChatModel(String email_Id, String full_Name, String status) {
        Email_Id = email_Id;
        Full_Name = full_Name;
        this.status = status;
    }

    public String getEmail_Id() {
        return Email_Id;
    }

    public void setEmail_Id(String email_Id) {
        Email_Id = email_Id;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
