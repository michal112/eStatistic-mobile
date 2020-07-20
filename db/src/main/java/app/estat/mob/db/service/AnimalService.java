package app.estat.mob.db.service;

import app.estat.mob.db.dao.DaoSession;

public interface AnimalService<T> extends Service<T> {
    Long save(DaoSession daoSession, T entity);

    void delete(DaoSession daoSession, String publicId);
}
