package app.estat.mob.mvp.presenter.module;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.event.UserImageChangeStartEvent;
import app.estat.mob.event.UserImageChangedEvent;
import app.estat.mob.mvp.core.MvpBaseActivityPresenter;
import app.estat.mob.mvp.view.module.ModuleActivityView;

public class ModuleActivityPresenter extends MvpBaseActivityPresenter<ModuleActivityView> {
    private ModuleHandlerThread mHandlerThread;

    public ModuleActivityPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);

        mHandlerThread = new ModuleHandlerThread();
        mHandlerThread.start();
        mHandlerThread.getLooper();
    }

    public void scaleUserImage(final Context context, final Uri imageUri) {
        mHandlerThread.post(new Runnable() {
            @Override
            public void run() {
                getModuleWrapper().getEventBus().post(new UserImageChangeStartEvent());

                getModuleWrapper().getImageManager().scaleUserImage(context, imageUri);

                getModuleWrapper().getEventBus().post(new UserImageChangedEvent());
            }
        });
    }

    private class ModuleHandlerThread extends HandlerThread {
        private Handler mHandler;

        public ModuleHandlerThread() {
            super(ModuleHandlerThread.class.getName());
        }

        @Override
        protected void onLooperPrepared() {
            mHandler = new Handler(getLooper());
        }

        public void post(Runnable runnable) {
            mHandler.post(runnable);
        }
    }
}
