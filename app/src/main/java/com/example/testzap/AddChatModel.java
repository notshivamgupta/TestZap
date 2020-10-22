package com.example.testzap;

public class AddChatModel {
    String Email_Id,Full_Name,status,User_Id;
    Long test_completed,time_taken;

    public AddChatModel() {
    }

    public AddChatModel(String email_Id, String full_Name, String status, String user_Id, Long test_completed, Long time_taken) {
        Email_Id = email_Id;
        Full_Name = full_Name;
        this.status = status;
        User_Id = user_Id;
        this.test_completed = test_completed;
        this.time_taken = time_taken;
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

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public Long getTest_completed() {
        return test_completed;
    }

    public void setTest_completed(Long test_completed) {
        this.test_completed = test_completed;
    }

    public Long getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(Long time_taken) {
        this.time_taken = time_taken;
    }
}
