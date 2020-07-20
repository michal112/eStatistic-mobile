package app.estat.mob.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.entity.Bull;
import app.estat.mob.db.entity.Cow;
import app.estat.mob.db.entity.Lactation;
import app.estat.mob.db.entity.Mate;
import app.estat.mob.db.entity.Module;
import app.estat.mob.db.service.BullService;
import app.estat.mob.db.service.CowService;
import app.estat.mob.db.service.LactationService;
import app.estat.mob.db.service.MateService;
import app.estat.mob.db.service.ModuleService;

public class DbCache {
    private ModuleService mModuleService = new ModuleService();

    private CowService mCowService = new CowService();

    private BullService mBullService = new BullService();

    private MateService mMateService = new MateService();

    private LactationService mLactationService = new LactationService();

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

    private void prefetchCows(DaoSession session) {
        mCows = mCowService.getAll(session);
        for (Cow cow : mCows) {
            cow.getMates();
            cow.getLactations();
        }
    }

    private void prefetchBulls(DaoSession session) {
        mBulls = mBullService.getAll(session);
        for (Bull bull : mBulls) {
            bull.getMates();
        }
    }

    private void prefetchModules(DaoSession session) {
        mModules = mModuleService.getAll(session);
    }

    public void saveCow(DaoSession session, Cow cow) {
        mCowService.save(session, cow);
        mCows.add(cow);
    }

    public void saveBull(DaoSession session, Bull bull) {
        mBullService.save(session, bull);
        mBulls.add(bull);
    }

    public void saveMate(DaoSession session, Mate mate) {
        mMateService.save(session, mate);
        findCowByPublicId(mate.getCow().getPublicId()).getMates().add(mate);
        findBullByPublicId(mate.getBull().getPublicId()).getMates().add(mate);
    }

    public void saveLactation(DaoSession session, Lactation lactation) {
        mLactationService.save(session, lactation);
        lactation.getCow().getLactations().add(lactation);
    }

    public void deleteCow(DaoSession session, String cowPublicId) {
        Cow cow = findCowByPublicId(cowPublicId);

        List<Mate> bullMates = new ArrayList<>();
        for (Mate cowMate : cow.getMates()) {
            Bull bull = findBullByPublicId(cowMate.getBull().getPublicId());
            for (Mate bullMate : bull.getMates()) {
                if (cowMate.getPublicId().equals(bullMate.getPublicId())) {
                    bullMates.add(bullMate);
                }
            }
            bull.getMates().removeAll(bullMates);
        }

        deleteMates(session, cow.getMates());
        detachMates(session, bullMates);
        detachMates(session, cow.getMates());
        deleteLactations(session, cow.getLactations());
        mCows.remove(cow);
        mCowService.delete(session, cowPublicId);
    }

    private void detachMates(DaoSession session, List<Mate> mates) {
        for (Mate mate : mates) {
            session.getMateDao().detach(mate);
        }
    }

    public void deleteBull(DaoSession session, String bullPublicId) {
        Bull bull = findBullByPublicId(bullPublicId);
        //deleteMates(session, bull.getMates());
        mBulls.remove(bull);
        mBullService.delete(session, bullPublicId);
    }

    public void deleteMate(DaoSession session, Mate mate) {
        deleteMates(session, Collections.singletonList(mate));
    }

    private void deleteMates(DaoSession session, List<Mate> mates) {
        List<String> publicIdList = new ArrayList<>();
        for (Mate mate : mates) {
            publicIdList.add(mate.getPublicId());
        }
        mMateService.deleteAll(session, publicIdList);
    }

    public void deleteLactation(DaoSession session, Lactation lactation) {
        deleteLactations(session, Collections.singletonList(lactation));
    }

    private void deleteLactations(DaoSession session, List<Lactation> lactations) {
        List<String> publicIdList = new ArrayList<>();
        for (Lactation lactation : lactations) {
            publicIdList.add(lactation.getPublicId());
            session.getLactationDao().detach(lactation);
        }
        mLactationService.deleteAll(session, publicIdList);
    }

    public Cow findCowByPublicId(String publicId) {
        for (Cow cow : mCows) {
            if (publicId.equals(cow.getPublicId())) {
                return cow;
            }
        }
        return null;
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
