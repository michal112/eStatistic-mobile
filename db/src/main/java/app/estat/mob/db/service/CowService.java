package app.estat.mob.db.service;

import java.util.List;

import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.entity.Cow;

public class CowService implements Service<Cow> {
    @Override
    public List<Cow> getAll(DaoSession daoSession) {
        return daoSession.getCowDao().queryBuilder().list();
    }
}
