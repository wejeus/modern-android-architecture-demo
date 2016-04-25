package com.screeninteraction.architecturedemo.system;

import android.content.Context;
import android.support.annotation.StringRes;

public class DefaultStringProvider implements StringProvider {

    private final Context context;

    public DefaultStringProvider(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public String getString(@StringRes int stringId) {
        return context.getString(stringId);
    }

}