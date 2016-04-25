package com.screeninteraction.architecturedemo.io;


import com.screeninteraction.architecturedemo.rest.LoginRest;
import com.screeninteraction.architecturedemo.system.PreferencesProvider;
import com.screeninteraction.architecturedemo.utils.TextUtils;

public class LoginInteractor implements AsyncTransaction {

    private PreferencesProvider preferencesProvider;

    private String username;
    private String password;

    public LoginInteractor(PreferencesProvider preferencesProvider) {
        this.preferencesProvider = preferencesProvider;
    }

    public static class Response implements InteractorResponse {
        public String welcomeMessage;
    }

    public void setRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean validate() {
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password);
    }

    @Override
    public void execute(final TransactionCallback callback) {
        // interact with several components
        preferencesProvider.set(PreferencesProvider.DefaultPrefs.USERNAME, username);

        LoginRest restCall = new LoginRest() {
            @Override
            protected void onPostExecute(String result) {
                if ("SUCCESS".equalsIgnoreCase(result)) {
                    Response response = new Response();
                    response.welcomeMessage = result;
                    callback.onSuccess(response);
                } else {
                    callback.onError("some error", 1);
                }
            }
        };

        restCall.execute(username, password);
    }
}
