package app.estat.mob.module;

import javax.inject.Singleton;

import app.estat.mob.mvp.model.SharedPreferencesManager;
import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {
    @Provides
    @Singleton
    public SharedPreferencesManager provideSharedPreferencesManager() {
        return new SharedPreferencesManager();
    }
}
