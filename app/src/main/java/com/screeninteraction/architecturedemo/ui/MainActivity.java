package com.screeninteraction.architecturedemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.screeninteraction.architecturedemo.R;

public class MainActivity extends AppCompatActivity {

    private TextView messageView;

    public static void startActivityInstance(Activity parent) {
        Intent intent = new Intent(parent, MainActivity.class);
        parent.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageView = (TextView) findViewById(R.id.message);
        messageView.setText("MainActivity (logged in)");
    }
}
