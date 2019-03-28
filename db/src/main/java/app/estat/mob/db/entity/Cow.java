package app.estat.mob.db.entity;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;

import app.estat.mob.db.converter.BookConverter;
import app.estat.mob.db.dao.CowDao;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.dao.LactationDao;
import app.estat.mob.db.dao.MateDao;
import app.estat.mob.db.type.Book;
import app.estat.mob.db.dao.BullDao;

@Entity(nameInDb = "COW")
public class Cow implements ModuleItem {
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;

    @Property(nameInDb = "PUBLIC_ID")
    private String publicId;

    @Property(nameInDb = "NAME")
    private String name;

    @Property(nameInDb = "NUMBER")
    private String number;

    @Convert(converter = BookConverter.class, columnType = String.class)
    @Property(nameInDb = "BOOK")
    private Book book;

    @Property(nameInDb = "BULL_ID")
    private Long bullId;

    @ToOne(joinProperty = "bullId")
    private Bull father;

    @Property(nameInDb = "BIRTHDAY")
    private Date birthday;

    @ToMany(referencedJoinProperty = "cowId")
    private List<Lactation> lactations;

    @ToMany(referencedJoinProperty = "cowId")
    private List<Mate> mates;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1561406415)
    private transient CowDao myDao;

    @Generated(hash = 950934642)
    public Cow(Long id, String publicId, String name, String number, Book book, Long bullId,
            Date birthday) {
        this.id = id;
        this.publicId = publicId;
        this.name = name;
        this.number = number;
        this.book = book;
        this.bullId = bullId;
        this.birthday = birthday;
    }

    @Generated(hash = 1716523797)
    public Cow() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Generated(hash = 2100996716)
    private transient Long father__resolvedKey;

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1851007477)
    public List<Lactation> getLactations() {
        if (lactations == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LactationDao targetDao = daoSession.getLactationDao();
            List<Lactation> lactationsNew = targetDao._queryCow_Lactations(id);
            synchronized (this) {
                if(lactations == null) {
                    lactations = lactationsNew;
                }
            }
        }
        return lactations;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 171945337)
    public synchronized void resetLactations() {
        lactations = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1276745217)
    public List<Mate> getMates() {
        if (mates == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MateDao targetDao = daoSession.getMateDao();
            List<Mate> matesNew = targetDao._queryCow_Mates(id);
            synchronized (this) {
                if(mates == null) {
                    mates = matesNew;
                }
            }
        }
        return mates;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 351037583)
    public synchronized void resetMates() {
        mates = null;
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
    @Generated(hash = 420341432)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCowDao() : null;
    }

    public String getPublicId() {
        return this.publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Long getBullId() {
        return this.bullId;
    }

    public void setBullId(Long bullId) {
        this.bullId = bullId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 48047428)
    public Bull getFather() {
        Long __key = this.bullId;
        if (father__resolvedKey == null || !father__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BullDao targetDao = daoSession.getBullDao();
            Bull fatherNew = targetDao.load(__key);
            synchronized (this) {
                father = fatherNew;
                father__resolvedKey = __key;
            }
        }
        return father;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 962725852)
    public void setFather(Bull father) {
        synchronized (this) {
            this.father = father;
            bullId = father == null ? null : father.getId();
            father__resolvedKey = bullId;
        }
    }
}
