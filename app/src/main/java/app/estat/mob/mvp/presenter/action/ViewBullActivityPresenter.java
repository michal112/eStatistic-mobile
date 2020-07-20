package app.estat.mob.mvp.presenter.action;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.event.AdapterRefreshEvent;
import app.estat.mob.event.BullDeletedEvent;
import app.estat.mob.event.MateDeletedEvent;
import app.estat.mob.event.StatusEvent;
import app.estat.mob.mvp.util.PresenterUtils;
import app.estat.mob.mvp.view.action.ViewBullActivityView;

public class ViewBullActivityPresenter extends ActionActivityPresenter<ViewBullActivityView> {

    private static final String TAG = ViewBullActivityPresenter.class.getName();

    private PresenterUtils.PresenterHandler mDeleteBullHandlerThread;

    public ViewBullActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

        mDeleteBullHandlerThread = new PresenterUtils.PresenterHandler(ViewBullActivityPresenter.class.getName());
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
                    Log.e(TAG, "unable to delete bull", ex);
                    getModuleWrapper().getEventBus().post(new BullDeletedEvent(StatusEvent.Status.FAILURE));
                }
                getModuleWrapper().getEventBus().post(new BullDeletedEvent(StatusEvent.Status.SUCCESS));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMateDeletedEvent(MateDeletedEvent event) {
        if (!isViewAttached()) {
            return;
        }

        if (StatusEvent.Status.SUCCESS.equals(event.getStatus())) {
            getView().showMessage(R.string.mate_successfully_deleted);
        } else if (StatusEvent.Status.FAILURE.equals(event.getStatus())) {
            getView().showMessage(R.string.mate_delete_error);
        }
    }
}
