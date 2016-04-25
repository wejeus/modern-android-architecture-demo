package com.screeninteraction.architecturedemo.io;


public interface AsyncTransaction {

    boolean validate();

    void execute(final TransactionCallback callback);

}
