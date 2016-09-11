package app.estat.mob.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.dao.CowDao;
import app.estat.mob.db.dao.MateDao;
import app.estat.mob.db.dao.BullDao;

@Entity(nameInDb = "MATE")
public class Mate {
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;

    @Property(nameInDb = "DATE")
    private Date date;

    private Long cowId;

    @ToOne(joinProperty = "cowId")
    private Cow cow;

    private Long bullId;

    @ToOne(joinProperty = "bullId")
    private Bull bull;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1609347472)
    private transient MateDao myDao;

    @Generated(hash = 1480617541)
    public Mate(Long id, Date date, Long cowId, Long bullId) {
        this.id = id;
        this.date = date;
        this.cowId = cowId;
        this.bullId = bullId;
    }

    @Generated(hash = 813292027)
    public Mate() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCowId() {
        return this.cowId;
    }

    public void setCowId(Long cowId) {
        this.cowId = cowId;
    }

    public Long getBullId() {
        return this.bullId;
    }

    public void setBullId(Long bullId) {
        this.bullId = bullId;
    }

    @Generated(hash = 2081361528)
    private transient Long cow__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 646019015)
    public Cow getCow() {
        Long __key = this.cowId;
        if (cow__resolvedKey == null || !cow__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CowDao targetDao = daoSession.getCowDao();
            Cow cowNew = targetDao.load(__key);
            synchronized (this) {
                cow = cowNew;
                cow__resolvedKey = __key;
            }
        }
        return cow;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 278215627)
    public void setCow(Cow cow) {
        synchronized (this) {
            this.cow = cow;
            cowId = cow == null ? null : cow.getId();
            cow__resolvedKey = cowId;
        }
    }

    @Generated(hash = 153686614)
    private transient Long bull__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 615501688)
    public Bull getBull() {
        Long __key = this.bullId;
        if (bull__resolvedKey == null || !bull__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BullDao targetDao = daoSession.getBullDao();
            Bull bullNew = targetDao.load(__key);
            synchronized (this) {
                bull = bullNew;
                bull__resolvedKey = __key;
            }
        }
        return bull;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 484539360)
    public void setBull(Bull bull) {
        synchronized (this) {
            this.bull = bull;
            bullId = bull == null ? null : bull.getId();
            bull__resolvedKey = bullId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1836040247)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMateDao() : null;
    }
}
