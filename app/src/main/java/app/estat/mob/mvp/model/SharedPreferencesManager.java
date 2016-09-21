package app.estat.mob.mvp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;

public class SharedPreferencesManager {
    public final static String USER_NAME_KEY = "app.estat.mob.mvp.model.SharedPreferencesManager.USER_NAME_KEY";

    public final static String BARN_NUMBER_KEY = "app.estat.mob.mvp.model.SharedPreferencesManager.BARN_NUMBER_KEY";

    public final static String FARM_ADDRESS_KEY = "app.estat.mob.mvp.model.SharedPreferencesManager.FARM_ADDRESS_KEY";

    public void saveStringValue(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(key, value).apply();
    }

    public String getStringValue(Context context, String key, @StringRes int defVal) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, context.getResources().getString(defVal));
    }

    public String getStringValue(Context context, String key, String defVal) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, defVal);
    }
}
