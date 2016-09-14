package app.estat.mob.db.entity;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import app.estat.mob.db.converter.ActivityConverter;
import app.estat.mob.db.type.Activity;

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

    @Property(nameInDb = "ACTIVITY")
    @Convert(columnType = String.class, converter = ActivityConverter.class)
    private Activity activity;

    @Generated(hash = 2041164382)
    public Module(long id, String nameRes, String iconRes, String descriptionRes,
            Activity activity) {
        this.id = id;
        this.nameRes = nameRes;
        this.iconRes = iconRes;
        this.descriptionRes = descriptionRes;
        this.activity = activity;
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

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return this.activity;
    }
}
