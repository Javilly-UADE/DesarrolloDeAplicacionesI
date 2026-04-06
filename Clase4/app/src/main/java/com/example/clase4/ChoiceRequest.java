package com.example.clase4;

public class ChoiceRequest {

    private String userName;
    private String choice;

    public ChoiceRequest(String userName, String choice) {
        this.userName = userName;
        this.choice = choice;
    }

    public String getUserName() {
        return userName;
    }

    public String getChoice() {
        return choice;
    }
}