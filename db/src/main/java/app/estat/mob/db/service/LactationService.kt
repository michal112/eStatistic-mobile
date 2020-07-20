package app.estat.mob.db.service

import app.estat.mob.db.dao.DaoSession
import app.estat.mob.db.dao.LactationDao
import app.estat.mob.db.entity.Lactation

class LactationService : AnimalService<Lactation> {
    override fun save(daoSession: DaoSession?, lactation: Lactation?): Long? {
        return daoSession?.insert(lactation)
    }

    override fun getAll(daoSession: DaoSession?): MutableList<Lactation>? {
        return daoSession?.lactationDao?.queryBuilder()?.list()
    }

    override fun delete(daoSession: DaoSession?, publicId: String?) {
        daoSession?.lactationDao?.queryBuilder()
                ?.where(LactationDao.Properties.PublicId.eq(publicId))
                ?.buildDelete()
                ?.executeDeleteWithoutDetachingEntities()
    }

    fun deleteAll(daoSession: DaoSession?, publicIdList: List<String>?) {
        daoSession?.lactationDao?.queryBuilder()
                ?.where(LactationDao.Properties.PublicId.`in`(publicIdList))
                ?.buildDelete()
                ?.executeDeleteWithoutDetachingEntities()
    }
}