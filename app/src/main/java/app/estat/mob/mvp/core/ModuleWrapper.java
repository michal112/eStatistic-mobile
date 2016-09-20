package app.estat.mob.mvp.core;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.DbCache;
import app.estat.mob.db.DbManager;
import app.estat.mob.mvp.model.ImageManager;

public class ModuleWrapper {
    @Inject
    EventBus mEventBus;

    @Inject
    DbManager mDbManager;

    @Inject
    DbCache mDbCache;

    @Inject
    ImageManager mImageManager;

    public ModuleWrapper(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public EventBus getEventBus() {
        return mEventBus;
    }

    public DbCache getDbCache() {
        return mDbCache;
    }

    public DbManager getDbManager() {
        return mDbManager;
    }

    public ImageManager getImageManager() {
        return mImageManager;
    }
}
