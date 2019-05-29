package app.estat.mob.module;

import javax.inject.Singleton;

import app.estat.mob.mvp.model.SynchronizationManager;
import dagger.Module;
import dagger.Provides;

@Module
public class SynchronizationModule {

    @Provides
    @Singleton
    public SynchronizationManager provideSyncchronizationManager() {
        return new SynchronizationManager();
    }
}
