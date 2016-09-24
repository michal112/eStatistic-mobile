package app.estat.mob.component;

import javax.inject.Singleton;

import app.estat.mob.module.DbModule;
import app.estat.mob.module.EventBusModule;
import app.estat.mob.module.ImageModule;
import app.estat.mob.module.PreferencesModule;
import app.estat.mob.mvp.core.ModuleWrapper;
import app.estat.mob.ui.SplashScreenActivity;
import dagger.Component;

@Singleton
@Component(modules = {DbModule.class, EventBusModule.class,
        ImageModule.class, PreferencesModule.class})
public interface ApplicationComponent {
    void inject(SplashScreenActivity splashScreenActivity);

    void inject(ModuleWrapper moduleWrapper);
}
