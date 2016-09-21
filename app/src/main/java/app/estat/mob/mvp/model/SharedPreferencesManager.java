package app.estat.mob.mvp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import app.estat.mob.R;

public class SharedPreferencesManager {
    private final static String USER_NAME_KEY = "app.estat.mob.mvp.model.SharedPreferencesManager.USER_NAME_KEY";

    private Context mContext;

    public void saveUserName(Context context, String name) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(USER_NAME_KEY, name).apply();
    }

    public String getUserName(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(USER_NAME_KEY, mContext.getResources().getString(R.string.drawer_user_unknown));
    }
}
