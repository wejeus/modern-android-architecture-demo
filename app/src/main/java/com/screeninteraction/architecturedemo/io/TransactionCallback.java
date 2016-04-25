package com.screeninteraction.architecturedemo.io;


public abstract class TransactionCallback {

    public abstract void onSuccess(InteractorResponse interactorResponse);

    public abstract void onError(String errorMessage, int errorCode);

}
