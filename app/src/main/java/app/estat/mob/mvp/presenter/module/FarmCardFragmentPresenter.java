package app.estat.mob.mvp.presenter.module;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.event.FormImageChangeStartEvent;
import app.estat.mob.event.FormImageChangeEndEvent;
import app.estat.mob.module.PreferencesModule;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.model.FarmData;
import app.estat.mob.mvp.model.ImageManager;
import app.estat.mob.mvp.model.SharedPreferencesManager;
import app.estat.mob.mvp.view.module.FarmCardFragmentView;

public class FarmCardFragmentPresenter extends MvpBaseFragmentPresenter<FarmCardFragmentView> {
    private static final String TAG = FarmCardFragmentPresenter.class.getName();

    private ImageHandlerThread mHandlerThread;

    public FarmCardFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

        mHandlerThread = new ImageHandlerThread();
        mHandlerThread.start();
        mHandlerThread.getLooper();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFormImageChangeStartEvent(FormImageChangeStartEvent formImageChangeStartEvent) {
        if (!isViewAttached()) {
            return;
        }

        getView().showImageProgress(formImageChangeStartEvent.getImageType());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFormImageChangeEndEvent(FormImageChangeEndEvent formImageChangeEndEvent) {
        if (!isViewAttached()) {
            return;
        }

        getView().refreshImage(formImageChangeEndEvent.getImageType(), formImageChangeEndEvent.getImageUri());
    }

    public Uri createUserImageTemp(Context context) {
        try {
            return getModuleWrapper().getImageManager().createUserImageTemp(context);
        } catch (IOException e) {
            Log.d(TAG, "An error occurred while creating image file", e);
            return null;
        }
    }

    public Uri createFarmImageTemp(Context context) {
        try {
            return getModuleWrapper().getImageManager().createFarmImageTemp(context);
        } catch (IOException e) {
            Log.d(TAG, "An error occurred while creating image file", e);
            return null;
        }
    }

    public void scaleImageTemp(final Context context, final int imageType) {
        mHandlerThread.post(new Runnable() {
            @Override
            public void run() {
                getModuleWrapper().getEventBus().post(new FormImageChangeStartEvent(imageType));
                Uri imageTempUri = null;
                try {
                    if (imageType == ImageManager.USER_IMAGE) {
                        imageTempUri = getModuleWrapper().getImageManager().getUserImageTempUri();
                        getModuleWrapper().getImageManager().scaleImage(context, imageType);
                    } else if (imageType == ImageManager.FARM_IMAGE) {
                        imageTempUri = getModuleWrapper().getImageManager().getFarmImageTempUri();
                        getModuleWrapper().getImageManager().scaleImage(context, imageType);
                    }
                } catch (IOException e) {
                    Log.d(TAG, "An error occurred while scaling image", e);
                } finally {
                    getModuleWrapper().getEventBus().post(new FormImageChangeEndEvent(imageType, imageTempUri));
                }
            }
        });
    }

    public void saveFarmData(Context context, FarmData farmData) {
        getModuleWrapper().getPreferencesManager()
            .saveStringValue(context, SharedPreferencesManager.USER_NAME_KEY, farmData.getUserName());
        getModuleWrapper().getPreferencesManager()
                .saveStringValue(context, SharedPreferencesManager.FARM_ADDRESS_KEY, farmData.getFarmAddress());
        getModuleWrapper().getPreferencesManager()
                .saveStringValue(context, SharedPreferencesManager.BARN_NUMBER_KEY, farmData.getBarnNumber());
    }


    private class ImageHandlerThread extends HandlerThread {
        private Handler mHandler;

        public ImageHandlerThread() {
            super(ImageHandlerThread.class.getName());
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
