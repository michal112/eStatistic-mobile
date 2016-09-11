package app.estat.mob.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "MODULE")
public class Module {
    @Id @Property(nameInDb = "ID")
    private long id;

    @Property(nameInDb = "NAME")
    private String nameRes;
    
    @Property(nameInDb = "ICON")
    private String iconRes;

    @Property(nameInDb = "DESCRIPTION")
    private String descriptionRes;

    @Generated(hash = 205690572)
    public Module(long id, String nameRes, String iconRes, String descriptionRes) {
        this.id = id;
        this.nameRes = nameRes;
        this.iconRes = iconRes;
        this.descriptionRes = descriptionRes;
    }

    @Generated(hash = 300059863)
    public Module() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameRes() {
        return this.nameRes;
    }

    public void setNameRes(String nameRes) {
        this.nameRes = nameRes;
    }

    public String getIconRes() {
        return this.iconRes;
    }

    public void setIconRes(String iconRes) {
        this.iconRes = iconRes;
    }

    public String getDescriptionRes() {
        return this.descriptionRes;
    }

    public void setDescriptionRes(String descriptionRes) {
        this.descriptionRes = descriptionRes;
    }
}
