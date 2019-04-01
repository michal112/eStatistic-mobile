package app.estat.mob.db;

import java.util.List;

import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.entity.Bull;
import app.estat.mob.db.entity.Cow;
import app.estat.mob.db.entity.Module;
import app.estat.mob.db.service.BullService;
import app.estat.mob.db.service.CowService;
import app.estat.mob.db.service.ModuleService;

public class DbCache {
    private ModuleService mModuleService = new ModuleService();

    private CowService mCowService = new CowService();

    private BullService mBullService = new BullService();

    private List<Module> mModules;

    private List<Cow> mCows;

    private List<Bull> mBulls;

    public List<Module> getModules() {
        return mModules;
    }

    public List<Cow> getCows() {
        return mCows;
    }

    public List<Bull> getBulls() {
        return mBulls;
    }

    public void prefetchData(DaoSession session) {
        prefetchModules(session);
        prefetchCows(session);
        prefetchBulls(session);
    }

    public void prefetchCows(DaoSession session) {
        mCows = mCowService.getAll(session);
    }

    public void prefetchBulls(DaoSession session) {
        mBulls = mBullService.getAll(session);
    }

    private void prefetchModules(DaoSession session) {
        mModules = mModuleService.getAll(session);
    }

    public long saveCow(DaoSession session, Cow cow) {
        return mCowService.save(session, cow);
    }

    public long saveBull(DaoSession session, Bull bull) {
        return mBullService.save(session, bull);
    }

    public Cow findCowByPublicId(DaoSession session, String publicId) {
        return mCowService.findByPublicId(session, publicId);
    }

    public Cow findCowByPublicId(String publicId) {
        for (Cow cow : mCows) {
            if (publicId.equals(cow.getPublicId())) {
                return cow;
            }
        }
        return null;
    }

    public Bull findBullByPublicId(DaoSession session, String publicId) {
        return mBullService.findByPublicId(session, publicId);
    }

    public Bull findBullByPublicId(String publicId) {
        for (Bull bull : mBulls) {
            if (publicId.equals(bull.getPublicId())) {
                return bull;
            }
        }
        return null;
    }
}
