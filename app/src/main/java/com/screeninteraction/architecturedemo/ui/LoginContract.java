package com.screeninteraction.architecturedemo.ui;


public interface LoginContract {

    interface View {

        String getUsername();
        String getPassword();
        void setLoginButtonEnabled(boolean enabled);
        void setInputEnabled(boolean enabled);
        void showError(String message);
        void startMainActivity();
        void showLoadingIndicator(boolean visible);
        void showInfo(String message);

    }

    interface Actions {

        void enableLoginButtonIfPossible();
        void onInputChanged();
        void onLoginButtonClick();
        void didInitializeView();
        void toggleLoadingState(boolean isLoading);

    }
}
