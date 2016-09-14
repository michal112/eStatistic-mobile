package app.estat.mob.db.converter;

import android.util.Log;

import org.greenrobot.greendao.converter.PropertyConverter;
import app.estat.mob.db.type.Book;

public class BookConverter implements PropertyConverter<Book, String> {
    private static final String TAG = BookConverter.class.getName();

    @Override
    public Book convertToEntityProperty(String databaseValue) {
        try {
            return Enum.valueOf(Book.class, databaseValue);
        } catch (IllegalArgumentException ex) {
            Log.w(TAG, "Failed to convert value from the database.", ex);
        }

        return Book.UNKNOWN;
    }

    @Override
    public String convertToDatabaseValue(Book entityProperty) {
        return entityProperty.name();
    }
}