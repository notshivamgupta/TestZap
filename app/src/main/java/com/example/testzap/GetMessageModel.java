package com.example.testzap;

public class GetMessageModel {
   private String Message,Receiver,Sender;

    public GetMessageModel() {
    }

    public GetMessageModel(String message, String receiver, String sender) {
        Message = message;
        Receiver = receiver;
        Sender = sender;
    }


    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }
}
