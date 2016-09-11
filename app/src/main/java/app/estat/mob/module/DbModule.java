package app.estat.mob.module;

import javax.inject.Singleton;

import app.estat.mob.db.DbCache;
import app.estat.mob.db.DbManager;
import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {
    @Provides
    @Singleton
    public DbCache provideDbCache() {
        return new DbCache();
    }

    @Provides
    @Singleton
    public DbManager provideDbManager() {
        return new DbManager();
    }
}
