package com.screeninteraction.architecturedemo.io.usecases;

import com.screeninteraction.architecturedemo.io.AsyncTransaction;
import com.screeninteraction.architecturedemo.io.InteractorRequest;
import com.screeninteraction.architecturedemo.io.TransactionCallback;
import com.screeninteraction.architecturedemo.rest.LoginRest;
import com.screeninteraction.architecturedemo.system.PreferencesProvider;
import com.screeninteraction.architecturedemo.utils.TextUtils;

public class LoginInteractor implements AsyncTransaction {

    private PreferencesProvider preferencesProvider;

    private LoginRequest loginRequest;

    public LoginInteractor(PreferencesProvider preferencesProvider) {
        this.preferencesProvider = preferencesProvider;
    }

    public void setRequest(InteractorRequest loginRequest) {
        this.loginRequest = (LoginRequest) loginRequest;
    }

    @Override
    public boolean validate() {
        return !TextUtils.isEmpty(
                    loginRequest.getUsername()) &&
                    !TextUtils.isEmpty(loginRequest.getPassword()
                );
    }

    @Override
    public void execute(final TransactionCallback callback) {
        // example of interaction with several components
        preferencesProvider.set(PreferencesProvider.DefaultPrefs.USERNAME, loginRequest.getUsername());

        LoginRest restCall = new LoginRest() {
            @Override
            protected void onPostExecute(String result) {
                if ("SUCCESS".equalsIgnoreCase(result)) {
                    LoginResponse response = new LoginResponse();
                    response.setWelcomeMessage(result);
                    callback.onSuccess(response);
                } else {
                    callback.onError("some error", 1);
                }
            }
        };

        restCall.execute(loginRequest.getUsername(), loginRequest.getPassword());
    }
}

