package app.estat.mob.db.service;

import java.util.List;

import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.entity.Module;

public class ModuleService implements Service<Module> {
    @Override
    public List<Module> getAll(DaoSession daoSession) {
        return daoSession.getModuleDao().queryBuilder().list();
    }
}
