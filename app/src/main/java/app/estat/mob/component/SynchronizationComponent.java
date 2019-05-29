package app.estat.mob.component;

import javax.inject.Singleton;

import app.estat.mob.communication.service.SynchronizationService;
import app.estat.mob.module.SynchronizationModule;
import dagger.Component;

@Singleton
@Component(modules = {SynchronizationModule.class})
public interface SynchronizationComponent {

    void inject(SynchronizationService synchronizationService);
}
