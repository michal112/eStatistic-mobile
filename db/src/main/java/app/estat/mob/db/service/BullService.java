package app.estat.mob.db.service;

import java.util.List;

import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.entity.Bull;

public class BullService implements AnimalService<Bull> {
    @Override
    public List<Bull> getAll(DaoSession daoSession) {
        return daoSession.getBullDao().queryBuilder().list();
    }

    @Override
    public long save(DaoSession daoSession, Bull bull) {
        return daoSession.insert(bull);
    }
}
