package app.estat.mob.mvp.presenter.action;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.type.Book;
import app.estat.mob.db.type.FormSpinnerItem;
import app.estat.mob.event.CowSavedEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.model.CowData;
import app.estat.mob.mvp.view.action.AddCowFragmentView;

public class AddCowFragmentPresenter extends MvpBaseFragmentPresenter<AddCowFragmentView> {

    private static final String TAG = AddCowFragmentPresenter.class.getName();

    private AddCowHandlerThread mCowHandlerThread;

    public AddCowFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

        mCowHandlerThread = new AddCowHandlerThread();
        mCowHandlerThread.start();
        mCowHandlerThread.getLooper();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCowSavedEvent(CowSavedEvent event) {
        if (!isViewAttached()) {
            return;
        }

        getView().setActivityResult(event.getStatus());
    }

    public List<FormSpinnerItem> getSpinnerData() {
        List<FormSpinnerItem> spinnerData = new ArrayList<>();
        spinnerData.addAll(Arrays.asList(Book.values()));
        return spinnerData;
    }

    public void saveCowData(final Context context, final CowData cowData) {
        mCowHandlerThread.post(new Runnable() {
            @Override
            public void run() {
                try {
                    DaoSession daoSession = getModuleWrapper().getDbManager().getDaoSession(context);
                    getModuleWrapper().getDbCache().saveCow(daoSession, cowData.getCow());
                } catch (Exception ex) {
                    Log.e(TAG, "unable to save cow", ex);
                    getModuleWrapper().getEventBus().post(new CowSavedEvent(CowSavedEvent.Status.FAILURE));
                }
                getModuleWrapper().getEventBus().post(new CowSavedEvent(CowSavedEvent.Status.SUCCESS));
            }
        });
    }

    private class AddCowHandlerThread extends HandlerThread {
        private Handler mHandler;

        public AddCowHandlerThread() {
            super(AddCowHandlerThread.class.getName());
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