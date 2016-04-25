package com.screeninteraction.architecturedemo.system;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class DefaultPreferencesProvider implements PreferencesProvider {

    private SharedPreferences preferences;

    public DefaultPreferencesProvider(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public String get(String key) {
        return preferences.getString(key, "");
    }

    @Override
    public void set(DefaultPrefs key, String value) {
        preferences.edit().putString(key.toString(), value).commit();
    }
}
