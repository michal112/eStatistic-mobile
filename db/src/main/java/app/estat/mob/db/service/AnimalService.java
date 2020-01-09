package app.estat.mob.db.service;

import app.estat.mob.db.dao.DaoSession;

public interface AnimalService<T> extends Service<T> {
    long save(DaoSession daoSession, T entity);

    T findByPublicId(DaoSession daoSession, String publicId);

    void delete(DaoSession daoSession, String publicId);
}
