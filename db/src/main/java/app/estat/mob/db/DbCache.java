package app.estat.mob.db;

import java.util.List;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.entity.Module;
import app.estat.mob.db.service.ModuleService;

public class DbCache {
    private List<Module> mModules;

    public List<Module> getModules() {
        return mModules;
    }

    public void prefetchData(DaoSession session) {
        prefetchModules(session);
    }

    private void prefetchModules(DaoSession session) {
        mModules = ModuleService.getModules(session);
    }
}
