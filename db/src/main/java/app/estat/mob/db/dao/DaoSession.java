package app.estat.mob.db.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import app.estat.mob.db.entity.Cow;
import app.estat.mob.db.entity.Lactation;
import app.estat.mob.db.entity.Mate;
import app.estat.mob.db.entity.Sire;
import app.estat.mob.db.entity.Bull;
import app.estat.mob.db.entity.Module;

import app.estat.mob.db.dao.CowDao;
import app.estat.mob.db.dao.LactationDao;
import app.estat.mob.db.dao.MateDao;
import app.estat.mob.db.dao.SireDao;
import app.estat.mob.db.dao.BullDao;
import app.estat.mob.db.dao.ModuleDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cowDaoConfig;
    private final DaoConfig lactationDaoConfig;
    private final DaoConfig mateDaoConfig;
    private final DaoConfig sireDaoConfig;
    private final DaoConfig bullDaoConfig;
    private final DaoConfig moduleDaoConfig;

    private final CowDao cowDao;
    private final LactationDao lactationDao;
    private final MateDao mateDao;
    private final SireDao sireDao;
    private final BullDao bullDao;
    private final ModuleDao moduleDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cowDaoConfig = daoConfigMap.get(CowDao.class).clone();
        cowDaoConfig.initIdentityScope(type);

        lactationDaoConfig = daoConfigMap.get(LactationDao.class).clone();
        lactationDaoConfig.initIdentityScope(type);

        mateDaoConfig = daoConfigMap.get(MateDao.class).clone();
        mateDaoConfig.initIdentityScope(type);

        sireDaoConfig = daoConfigMap.get(SireDao.class).clone();
        sireDaoConfig.initIdentityScope(type);

        bullDaoConfig = daoConfigMap.get(BullDao.class).clone();
        bullDaoConfig.initIdentityScope(type);

        moduleDaoConfig = daoConfigMap.get(ModuleDao.class).clone();
        moduleDaoConfig.initIdentityScope(type);

        cowDao = new CowDao(cowDaoConfig, this);
        lactationDao = new LactationDao(lactationDaoConfig, this);
        mateDao = new MateDao(mateDaoConfig, this);
        sireDao = new SireDao(sireDaoConfig, this);
        bullDao = new BullDao(bullDaoConfig, this);
        moduleDao = new ModuleDao(moduleDaoConfig, this);

        registerDao(Cow.class, cowDao);
        registerDao(Lactation.class, lactationDao);
        registerDao(Mate.class, mateDao);
        registerDao(Sire.class, sireDao);
        registerDao(Bull.class, bullDao);
        registerDao(Module.class, moduleDao);
    }
    
    public void clear() {
        cowDaoConfig.clearIdentityScope();
        lactationDaoConfig.clearIdentityScope();
        mateDaoConfig.clearIdentityScope();
        sireDaoConfig.clearIdentityScope();
        bullDaoConfig.clearIdentityScope();
        moduleDaoConfig.clearIdentityScope();
    }

    public CowDao getCowDao() {
        return cowDao;
    }

    public LactationDao getLactationDao() {
        return lactationDao;
    }

    public MateDao getMateDao() {
        return mateDao;
    }

    public SireDao getSireDao() {
        return sireDao;
    }

    public BullDao getBullDao() {
        return bullDao;
    }

    public ModuleDao getModuleDao() {
        return moduleDao;
    }

}
