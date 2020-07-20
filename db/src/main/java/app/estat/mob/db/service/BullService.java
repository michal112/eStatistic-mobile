package app.estat.mob.db.service;

import java.util.List;

import app.estat.mob.db.dao.BullDao;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.entity.Bull;

public class BullService implements AnimalService<Bull> {
    @Override
    public List<Bull> getAll(DaoSession daoSession) {
        return daoSession.getBullDao().queryBuilder().list();
    }

    @Override
    public Long save(DaoSession daoSession, Bull bull) {
        return daoSession.insert(bull);
    }

    @Override
    public void delete(DaoSession daoSession, String publicId) {
        daoSession.getBullDao().queryBuilder()
                .where(BullDao.Properties.PublicId.eq(publicId))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }
}
