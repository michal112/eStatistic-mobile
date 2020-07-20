package app.estat.mob.mvp.presenter.action;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.entity.Bull;
import app.estat.mob.event.MateDeletedEvent;
import app.estat.mob.event.StatusEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.util.PresenterUtils;
import app.estat.mob.mvp.view.action.ViewBullFragmentView;

public class ViewBullFragmentPresenter extends MvpBaseFragmentPresenter<ViewBullFragmentView> {

    private static final String TAG = ViewBullFragmentPresenter.class.getName();

    private Bull mBull;

    private PresenterUtils.PresenterHandler mDeleteMateHandler;

    public ViewBullFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

        mDeleteMateHandler = new PresenterUtils.PresenterHandler(ViewBullFragmentPresenter.class.getName());
    }

    public Bull getBull() {
        return mBull;
    }

    public void setBull(String bullPublicId) {
        mBull = getModuleWrapper().getDbCache().findBullByPublicId(bullPublicId);
    }


    public void requestMates() {
        if (!isViewAttached()) {
            return;
        }

        getView().showMates(mBull.getMates());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMateDeletedEvent(MateDeletedEvent event) {
        if (!isViewAttached()) {
            return;
        }

        getView().refreshMates();
    }

    public void deleteMate(final Context context, final int position) {
        mDeleteMateHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    DaoSession daoSession = getModuleWrapper().getDbManager().getDaoSession(context);
                    getModuleWrapper().getDbCache().deleteMate(daoSession, mBull.getMates().get(position));
                } catch (Exception ex) {
                    Log.e(TAG, "unable to delete mate", ex);
                    getModuleWrapper().getEventBus().post(new MateDeletedEvent(StatusEvent.Status.FAILURE));
                }
                getModuleWrapper().getEventBus().post(new MateDeletedEvent(StatusEvent.Status.SUCCESS));
            }
        });

    }
}
