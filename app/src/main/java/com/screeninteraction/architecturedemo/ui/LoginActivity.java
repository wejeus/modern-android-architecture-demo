package com.screeninteraction.architecturedemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.screeninteraction.architecturedemo.R;
import com.screeninteraction.architecturedemo.io.LoginInteractor;
import com.screeninteraction.architecturedemo.system.DefaultPreferencesProvider;
import com.screeninteraction.architecturedemo.system.DefaultStringProvider;
import com.screeninteraction.architecturedemo.utils.AfterTextChangedWatcher;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private EditText usernameView;
    private EditText passwordView;
    private Button loginButton;
    private View progressView;

    private LoginContract.Actions presenter;

    private TextWatcher watcher = new AfterTextChangedWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            presenter.onInputChanged();
        }
    };

    private View.OnClickListener loginButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.onLoginButtonClick();
        }
    };

    @Override
    public void startMainActivity() {
        MainActivity.startActivityInstance(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameView = (EditText) findViewById(R.id.text_username);
        passwordView = (EditText) findViewById(R.id.text_password);
        loginButton = (Button) findViewById(R.id.button_login);
        progressView = findViewById(R.id.progress);

        presenter = new LoginPresenter(
                this,
                new LoginInteractor(new DefaultPreferencesProvider(this)),
                new DefaultStringProvider(this)
        );

        initViews();
    }

    private void initViews() {
        usernameView.addTextChangedListener(watcher);
        passwordView.addTextChangedListener(watcher);
        loginButton.setOnClickListener(loginButtonClickListener);

        presenter.didInitializeView();
    }

    @Override
    public String getUsername() {
        return usernameView.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordView.getText().toString();
    }

    @Override
    public void setLoginButtonEnabled(boolean enabled) {
        loginButton.setEnabled(enabled);
    }

    @Override
    public void setInputEnabled(boolean enabled) {
        usernameView.setEnabled(enabled);
        passwordView.setEnabled(enabled);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingIndicator(boolean visible) {
        if (visible) {
            progressView.setVisibility(View.VISIBLE);
        } else {
            progressView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showInfo(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
