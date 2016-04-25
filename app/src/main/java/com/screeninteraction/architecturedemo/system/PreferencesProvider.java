package com.screeninteraction.architecturedemo.system;


public interface PreferencesProvider {

    public enum DefaultPrefs {
        USERNAME
    }

    String get(String key);

    void set(DefaultPrefs key, String value);
}
