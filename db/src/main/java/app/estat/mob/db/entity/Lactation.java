package app.estat.mob.db.entity;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

import app.estat.mob.db.dao.CowDao;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.dao.LactationDao;

@Entity(nameInDb = "LACTATION")
public class Lactation implements FormItem {
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;

    @Property(nameInDb = "PUBLIC_ID")
    private String publicId;

    @Property(nameInDb = "NUMBER")
    private String number;

    @Property(nameInDb = "DATE")
    private Date date;

    @Property(nameInDb = "COW_ID")
    private Long cowId;

    @ToOne(joinProperty = "cowId")
    private Cow cow;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 28312843)
    private transient LactationDao myDao;

    @Generated(hash = 926821514)
    public Lactation(Long id, String publicId, String number, Date date, Long cowId) {
        this.id = id;
        this.publicId = publicId;
        this.number = number;
        this.date = date;
        this.cowId = cowId;
    }

    @Generated(hash = 1032010319)
    public Lactation() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
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
    @Generated(hash = 1798186539)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLactationDao() : null;
    }

    public String getPublicId() {
        return this.publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    @NotNull
    @Override
    public String getCowName() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public String getBullName() {
        throw new UnsupportedOperationException();
    }
}
