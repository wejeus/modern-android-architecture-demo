package com.screeninteraction.architecturedemo.rest;


import android.os.AsyncTask;

public class LoginRest extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        try {
            Thread.sleep(3000);
            return "SUCCESS";
        } catch (InterruptedException e) {}

        return "FAIL";
    }
}
