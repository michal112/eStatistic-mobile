package app.estat.mob.module;

import javax.inject.Singleton;

import app.estat.mob.mvp.model.ImageManager;
import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {
    @Provides
    @Singleton
    public ImageManager provideImageManager() {
        return new ImageManager();
    }
}
