package com.screeninteraction.architecturedemo.ui;

import com.screeninteraction.architecturedemo.R;
import com.screeninteraction.architecturedemo.io.InteractorResponse;
import com.screeninteraction.architecturedemo.io.LoginInteractor;
import com.screeninteraction.architecturedemo.io.TransactionCallback;
import com.screeninteraction.architecturedemo.system.StringProvider;
import com.screeninteraction.architecturedemo.utils.TextUtils;

public class LoginPresenter implements LoginContract.Actions {

    private final LoginContract.View view;
    private LoginInteractor interactor;
    private StringProvider strings;

    public LoginPresenter(LoginContract.View view, LoginInteractor interactor, StringProvider stringProvider) {
        this.view = view;
        this.interactor = interactor;
        this.strings = stringProvider;
    }

    @Override
    public void didInitializeView() {
        enableLoginButtonIfPossible();
    }

    @Override
    public void enableLoginButtonIfPossible() {
        String username = view.getUsername();
        String password = view.getPassword();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            view.setLoginButtonEnabled(false);
        } else {
            view.setLoginButtonEnabled(true);
        }
    }

    @Override
    public void onInputChanged() {
        enableLoginButtonIfPossible();
    }

    @Override
    public void onLoginButtonClick() {
        toggleLoadingState(true);

        interactor.setRequest(view.getUsername(), view.getPassword());
        interactor.execute(new TransactionCallback() {
            @Override
            public void onSuccess(InteractorResponse response) {
                toggleLoadingState(false);
                view.showInfo(((LoginInteractor.Response)response).welcomeMessage);
                view.startMainActivity();
            }

            @Override
            public void onError(String errorMessage, int errorCode) {
                toggleLoadingState(false);
                view.showError(strings.getString(R.string.login_error));
            }
        });
    }

    @Override
    public void toggleLoadingState(boolean isLoading) {
        if (isLoading) {
            view.setInputEnabled(false);
            view.showLoadingIndicator(true);
            view.setLoginButtonEnabled(false);
        } else {
            view.setInputEnabled(true);
            view.showLoadingIndicator(false);
            view.setLoginButtonEnabled(true);
        }
    }
}
