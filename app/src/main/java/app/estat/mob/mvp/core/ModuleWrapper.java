package app.estat.mob.mvp.core;

import javax.inject.Inject;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.DbCache;
import app.estat.mob.db.DbManager;

public class ModuleWrapper {
    @Inject
    DbManager mManager;

    @Inject
    DbCache mCache;

    public ModuleWrapper(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public DbCache getCache() {
        return mCache;
    }

    public DbManager getManager() {
        return mManager;
    }
}
