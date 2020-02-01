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
import app.estat.mob.event.FarmDataChangedEvent;
import app.estat.mob.event.FormImageChangedEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.model.FarmData;
import app.estat.mob.mvp.model.manager.ImageManager;
import app.estat.mob.mvp.model.manager.SharedPreferencesManager;
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
    public void onFarmDataChangedEvent(FarmDataChangedEvent farmDataChangedEvent) {
        if (!isViewAttached()) {
            return;
        }

        getView().setActivityResult(farmDataChangedEvent.getStatus());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFormImageChangeEndEvent(FormImageChangedEvent formImageChangedEvent) {
        if (!isViewAttached()) {
            return;
        }

        getView().showImage(formImageChangedEvent.getImageType(), formImageChangedEvent.getImageUri());
    }

    public Uri createImageTemp(Context context, int imageType) {
        try {
            return getModuleWrapper().getImageManager().createImageTemp(context, imageType);
        } catch (IOException e) {
            Log.d(TAG, "An error occurred while creating image file", e);
            return null;
        }
    }

    public void scaleImageTemp(final Context context, final int imageType) {
        mHandlerThread.post(new Runnable() {
            @Override
            public void run() {
                Uri imageTempUri = null;
                try {
                    if (imageType == ImageManager.USER_IMAGE) {
                        imageTempUri = getModuleWrapper().getImageManager().getUserImageTempUri();
                        getModuleWrapper().getImageManager().scaleTempImage(context, imageType);
                    } else if (imageType == ImageManager.FARM_IMAGE) {
                        imageTempUri = getModuleWrapper().getImageManager().getFarmImageTempUri();
                        getModuleWrapper().getImageManager().scaleTempImage(context, imageType);
                    }
                } catch (IOException e) {
                    Log.d(TAG, "An error occurred while scaling image", e);
                } finally {
                    getModuleWrapper().getEventBus().post(new FormImageChangedEvent(imageType, imageTempUri));
                }
            }
        });
    }

    public void saveFarmData(final Context context, final FarmData farmData) {
        mHandlerThread.post(new Runnable() {
            @Override
            public void run() {
                try {
                    getModuleWrapper().getImageManager().copyTempImage(context, ImageManager.FARM_IMAGE);
                    getModuleWrapper().getImageManager().copyTempImage(context, ImageManager.USER_IMAGE);
                    String userName = farmData.getUserName();
                    getModuleWrapper().getPreferencesManager()
                            .saveStringValue(context, SharedPreferencesManager.USER_NAME_KEY, userName);
                    getModuleWrapper().getPreferencesManager()
                            .saveStringValue(context, SharedPreferencesManager.FARM_ADDRESS_KEY, farmData.getFarmAddress());
                    getModuleWrapper().getPreferencesManager()
                            .saveStringValue(context, SharedPreferencesManager.BARN_NUMBER_KEY, farmData.getBarnNumber());

                    getModuleWrapper().getEventBus().post(new FarmDataChangedEvent(FarmDataChangedEvent.Status.SUCCESS));
                } catch (IOException e) {
                    Log.d(TAG, "Unable to copy temporary image", e);

                    getModuleWrapper().getEventBus().post(new FarmDataChangedEvent(FarmDataChangedEvent.Status.FAILURE));
                }
            }
        });
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
