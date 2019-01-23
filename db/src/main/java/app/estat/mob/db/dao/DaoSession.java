package app.estat.mob.db.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import app.estat.mob.db.entity.Bull;
import app.estat.mob.db.entity.Cow;
import app.estat.mob.db.entity.Lactation;
import app.estat.mob.db.entity.Mate;
import app.estat.mob.db.entity.Module;
import app.estat.mob.db.entity.Sire;

import app.estat.mob.db.dao.BullDao;
import app.estat.mob.db.dao.CowDao;
import app.estat.mob.db.dao.LactationDao;
import app.estat.mob.db.dao.MateDao;
import app.estat.mob.db.dao.ModuleDao;
import app.estat.mob.db.dao.SireDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig bullDaoConfig;
    private final DaoConfig cowDaoConfig;
    private final DaoConfig lactationDaoConfig;
    private final DaoConfig mateDaoConfig;
    private final DaoConfig moduleDaoConfig;
    private final DaoConfig sireDaoConfig;

    private final BullDao bullDao;
    private final CowDao cowDao;
    private final LactationDao lactationDao;
    private final MateDao mateDao;
    private final ModuleDao moduleDao;
    private final SireDao sireDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        bullDaoConfig = daoConfigMap.get(BullDao.class).clone();
        bullDaoConfig.initIdentityScope(type);

        cowDaoConfig = daoConfigMap.get(CowDao.class).clone();
        cowDaoConfig.initIdentityScope(type);

        lactationDaoConfig = daoConfigMap.get(LactationDao.class).clone();
        lactationDaoConfig.initIdentityScope(type);

        mateDaoConfig = daoConfigMap.get(MateDao.class).clone();
        mateDaoConfig.initIdentityScope(type);

        moduleDaoConfig = daoConfigMap.get(ModuleDao.class).clone();
        moduleDaoConfig.initIdentityScope(type);

        sireDaoConfig = daoConfigMap.get(SireDao.class).clone();
        sireDaoConfig.initIdentityScope(type);

        bullDao = new BullDao(bullDaoConfig, this);
        cowDao = new CowDao(cowDaoConfig, this);
        lactationDao = new LactationDao(lactationDaoConfig, this);
        mateDao = new MateDao(mateDaoConfig, this);
        moduleDao = new ModuleDao(moduleDaoConfig, this);
        sireDao = new SireDao(sireDaoConfig, this);

        registerDao(Bull.class, bullDao);
        registerDao(Cow.class, cowDao);
        registerDao(Lactation.class, lactationDao);
        registerDao(Mate.class, mateDao);
        registerDao(Module.class, moduleDao);
        registerDao(Sire.class, sireDao);
    }
    
    public void clear() {
        bullDaoConfig.clearIdentityScope();
        cowDaoConfig.clearIdentityScope();
        lactationDaoConfig.clearIdentityScope();
        mateDaoConfig.clearIdentityScope();
        moduleDaoConfig.clearIdentityScope();
        sireDaoConfig.clearIdentityScope();
    }

    public BullDao getBullDao() {
        return bullDao;
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

    public ModuleDao getModuleDao() {
        return moduleDao;
    }

    public SireDao getSireDao() {
        return sireDao;
    }

}
