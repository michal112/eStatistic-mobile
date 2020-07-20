package app.estat.mob.db.service

import app.estat.mob.db.dao.DaoSession
import app.estat.mob.db.dao.MateDao
import app.estat.mob.db.entity.Mate

class MateService : AnimalService<Mate> {
    override fun save(daoSession: DaoSession?, mate: Mate?): Long? {
        return daoSession?.insert(mate)
    }

    override fun getAll(daoSession: DaoSession?): MutableList<Mate>? {
        return daoSession?.mateDao?.queryBuilder()?.list()
    }

    override fun delete(daoSession: DaoSession?, publicId: String?) {
        daoSession?.mateDao?.queryBuilder()
                ?.where(MateDao.Properties.PublicId.eq(publicId))
                ?.buildDelete()
                ?.executeDeleteWithoutDetachingEntities()
    }

    fun deleteAll(daoSession: DaoSession?, publicIdList: List<String>?) {
        daoSession?.mateDao?.queryBuilder()
                ?.where(MateDao.Properties.PublicId.`in`(publicIdList))
                ?.buildDelete()
                ?.executeDeleteWithoutDetachingEntities()
    }
}