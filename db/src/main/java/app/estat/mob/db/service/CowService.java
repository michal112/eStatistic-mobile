package app.estat.mob.db.service;

import java.util.List;

import app.estat.mob.db.dao.CowDao;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.entity.Cow;

public class CowService implements AnimalService<Cow> {
    @Override
    public List<Cow> getAll(DaoSession daoSession) {
        return daoSession.getCowDao().queryBuilder().list();
    }

    @Override
    public Long save(DaoSession daoSession, Cow cow) {
        return daoSession.insert(cow);
    }

    @Override
    public void delete(DaoSession daoSession, String publicId) {
        daoSession.getCowDao().queryBuilder()
                .where(CowDao.Properties.PublicId.eq(publicId))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }
}
