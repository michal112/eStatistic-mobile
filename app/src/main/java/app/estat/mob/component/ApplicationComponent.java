package app.estat.mob.component;

import javax.inject.Singleton;

import app.estat.mob.module.DbModule;
import app.estat.mob.module.EventBusModule;
import app.estat.mob.mvp.core.ModuleWrapper;
import app.estat.mob.ui.SplashScreenActivity;

import dagger.Component;

@Singleton
@Component(modules = {DbModule.class, EventBusModule.class})
public interface ApplicationComponent {
    void inject(SplashScreenActivity splashScreenActivity);

    void inject(ModuleWrapper moduleWrapper);
}
