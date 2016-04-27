package com.screeninteraction.architecturedemo.io.usecases;

import com.screeninteraction.architecturedemo.io.InteractorResponse;

public class LoginResponse implements InteractorResponse {

    private String welcomeMessage;

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
}
