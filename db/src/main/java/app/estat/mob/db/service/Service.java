package app.estat.mob.db.service;

import java.util.List;

import app.estat.mob.db.dao.DaoSession;

interface Service<T> {
    List<T> getAll(DaoSession daoSession);
}
