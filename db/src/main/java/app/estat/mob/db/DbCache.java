package app.estat.mob.db;

import java.util.List;

import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.entity.Cow;
import app.estat.mob.db.entity.Module;
import app.estat.mob.db.service.CowService;
import app.estat.mob.db.service.ModuleService;

public class DbCache {
    private ModuleService mModuleService = new ModuleService();

    private CowService mCowService = new CowService();

    private List<Module> mModules;

    private List<Cow> mCows;

    public List<Module> getModules() {
        return mModules;
    }

    public List<Cow> getCows() {
        return mCows;
    }

    public void prefetchData(DaoSession session) {
        prefetchModules(session);
        prefetchCows(session);
    }

    private void prefetchCows(DaoSession session) {
        mCows = mCowService.getAll(session);
    }

    private void prefetchModules(DaoSession session) {
        mModules = mModuleService.getAll(session);
    }
}
