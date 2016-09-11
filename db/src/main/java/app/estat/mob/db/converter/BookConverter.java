package app.estat.mob.db.converter;

import org.greenrobot.greendao.converter.PropertyConverter;
import app.estat.mob.db.type.Book;

public class BookConverter implements PropertyConverter<Book, String> {
    @Override
    public Book convertToEntityProperty(String databaseValue) {
        return null;
    }

    @Override
    public String convertToDatabaseValue(Book entityProperty) {
        return entityProperty.name();
    }
}