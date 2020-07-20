package app.estat.mob.mvp.presenter.action;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.event.AdapterRefreshEvent;
import app.estat.mob.event.CowDeletedEvent;
import app.estat.mob.event.LactationAdapterRefreshEvent;
import app.estat.mob.event.LactationDeletedEvent;
import app.estat.mob.event.MateAdapterRefreshEvent;
import app.estat.mob.event.MateDeletedEvent;
import app.estat.mob.event.StatusEvent;
import app.estat.mob.mvp.util.PresenterUtils;
import app.estat.mob.mvp.view.action.ViewCowActivityView;
import app.estat.mob.ui.action.ViewBullActivity;

public class ViewCowActivityPresenter extends ActionActivityPresenter<ViewCowActivityView> {

    private static final String TAG = ViewCowActivityPresenter.class.getName();

    private PresenterUtils.PresenterHandler mDeleteCowHandlerThread;

    public ViewCowActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

        mDeleteCowHandlerThread = new PresenterUtils.PresenterHandler(ViewBullActivity.class.getName());
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
                getModuleWrapper().getEventBus().post(new CowDeletedEvent(StatusEvent.Status.SUCCESS));
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLactationDeletedEvent(LactationDeletedEvent event) {
        if (!isViewAttached()) {
            return;
        }

        if (StatusEvent.Status.SUCCESS.equals(event.getStatus())) {
            getView().showMessage(R.string.lactation_successfully_deleted);
        } else if (StatusEvent.Status.FAILURE.equals(event.getStatus())) {
            getView().showMessage(R.string.lactation_delete_error);
        }
    }

    public void sendMateAdapterRefreshEvent() {
        getModuleWrapper().getEventBus().post(new MateAdapterRefreshEvent());
    }

    public void sendLactationAdapterRefreshEvent() {
        getModuleWrapper().getEventBus().post(new LactationAdapterRefreshEvent());
    }
}
