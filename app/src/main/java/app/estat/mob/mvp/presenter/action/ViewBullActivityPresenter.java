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
import app.estat.mob.event.BullDeletedEvent;
import app.estat.mob.event.CowDeletedEvent;
import app.estat.mob.event.StatusEvent;
import app.estat.mob.mvp.view.action.ViewBullActivityView;

public class ViewBullActivityPresenter extends ActionActivityPresenter<ViewBullActivityView> {

    private static final String TAG = ViewBullActivityPresenter.class.getName();

    private DeleteBullHandlerThread mDeleteBullHandlerThread;

    public ViewBullActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

        mDeleteBullHandlerThread = new DeleteBullHandlerThread();
        mDeleteBullHandlerThread.start();
        mDeleteBullHandlerThread.getLooper();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBullDeletedEvent(BullDeletedEvent event) {
        if (!isViewAttached()) {
            return;
        }

        getView().deleteBull(event.getStatus());
        getModuleWrapper().getEventBus().post(new AdapterRefreshEvent());
    }

    public void deleteBull(final Context context, final String bullPublicId) {
        mDeleteBullHandlerThread.post(new Runnable() {
            @Override
            public void run() {
                try {
                    DaoSession daoSession = getModuleWrapper().getDbManager().getDaoSession(context);
                    getModuleWrapper().getDbCache().deleteBull(daoSession, bullPublicId);
                } catch (Exception ex) {
                    Log.e(TAG, "unable to delete cow", ex);
                    getModuleWrapper().getEventBus().post(new CowDeletedEvent(StatusEvent.Status.FAILURE));
                }
                getModuleWrapper().getEventBus().post(new CowDeletedEvent(StatusEvent.Status.FAILURE));
            }
        });
    }

    private class DeleteBullHandlerThread extends HandlerThread {
        private Handler mHandler;

        public DeleteBullHandlerThread() {
            super(DeleteBullHandlerThread.class.getName());
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
