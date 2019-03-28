package app.estat.mob.module;

import javax.inject.Singleton;

import app.estat.mob.mvp.model.SyncManager;
import dagger.Module;
import dagger.Provides;

@Module
public class SyncModule {

    @Provides
    @Singleton
    public SyncManager provideSyncManager() {
        return new SyncManager();
    }
}
