package app.estat.mob.db.converter;

import android.util.Log;

import org.greenrobot.greendao.converter.PropertyConverter;

import app.estat.mob.db.type.Activity;

public class ActivityConverter implements PropertyConverter<Activity, String> {
    private static final String TAG = ActivityConverter.class.getName();

    @Override
    public Activity convertToEntityProperty(String databaseValue) {
        try {
            return Enum.valueOf(Activity.class, databaseValue);
        } catch (IllegalArgumentException ex) {
            Log.w(TAG, "Failed to convert value from the database.", ex);
        }

        return Activity.UNKNOWN;
    }

    @Override
    public String convertToDatabaseValue(Activity entityProperty) {
        return entityProperty.name();
    }
}
