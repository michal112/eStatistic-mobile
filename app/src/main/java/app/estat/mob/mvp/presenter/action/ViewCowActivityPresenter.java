package app.estat.mob.mvp.presenter.action;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.event.AdapterRefreshEvent;
import app.estat.mob.event.CowDeletedEvent;
import app.estat.mob.event.StatusEvent;
import app.estat.mob.mvp.view.action.ViewCowActivityView;

public class ViewCowActivityPresenter extends ActionActivityPresenter<ViewCowActivityView> {

    private static final String TAG = ViewCowActivityPresenter.class.getName();

    private DeleteCowHandlerThread mDeleteCowHandlerThread;

    public ViewCowActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

        mDeleteCowHandlerThread = new DeleteCowHandlerThread();
        mDeleteCowHandlerThread.start();
        mDeleteCowHandlerThread.getLooper();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCowDeletedEvent(CowDeletedEvent event) {
        if (!isViewAttached()) {
            return;
        }

        getView().deleteCow(event.getStatus());
        getModuleWrapper().getEventBus().post(new AdapterRefreshEvent());
    }

    public void deleteCow(final Context context, final String cowPublicId) {
        mDeleteCowHandlerThread.post(new Runnable() {
            @Override
            public void run() {
                try {
                    DaoSession daoSession = getModuleWrapper().getDbManager().getDaoSession(context);
                    getModuleWrapper().getDbCache().deleteCow(daoSession, cowPublicId);
                } catch (Exception ex) {
                    Log.e(TAG, "unable to delete cow", ex);
                    getModuleWrapper().getEventBus().post(new CowDeletedEvent(StatusEvent.Status.FAILURE));
                }
                getModuleWrapper().getEventBus().post(new CowDeletedEvent(StatusEvent.Status.FAILURE));
            }
        });
    }

    private class DeleteCowHandlerThread extends HandlerThread {
        private Handler mHandler;

        public DeleteCowHandlerThread() {
            super(DeleteCowHandlerThread.class.getName());
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
