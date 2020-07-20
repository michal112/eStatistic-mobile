package app.estat.mob.mvp.presenter.action;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.db.entity.Cow;
import app.estat.mob.event.LactationAdapterRefreshEvent;
import app.estat.mob.event.LactationDeletedEvent;
import app.estat.mob.event.MateAdapterRefreshEvent;
import app.estat.mob.event.MateDeletedEvent;
import app.estat.mob.event.StatusEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.util.PresenterUtils;
import app.estat.mob.mvp.view.action.ViewCowFragmentView;

public class ViewCowFragmentPresenter extends MvpBaseFragmentPresenter<ViewCowFragmentView> {

    private static final String TAG = ViewCowFragmentPresenter.class.getName();

    private Cow mCow;

    private PresenterUtils.PresenterHandler mDeleteHandler;

    public ViewCowFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

        mDeleteHandler = new PresenterUtils.PresenterHandler(ViewCowFragmentPresenter.class.getName());
    }

    public Cow getCow() {
        return mCow;
    }

    public void setCow(String cowPublicId) {
        mCow = getModuleWrapper().getDbCache().findCowByPublicId(cowPublicId);
    }

    public void requestMates() {
        if (!isViewAttached()) {
            return;
        }

        getView().showMates(mCow.getMates());
    }

    public void requestLactations() {
        if (!isViewAttached()) {
            return;
        }

        getView().showLactations(mCow.getLactations());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMateAdapterRefreshEvent(MateAdapterRefreshEvent mateAdapterRefreshEvent) {
        if (!isViewAttached()) {
            return;
        }

        getView().refreshMates();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLactationAdapterRefreshEvent(LactationAdapterRefreshEvent lactationAdapterRefreshEvent) {
        if (!isViewAttached()) {
            return;
        }

        getView().refreshLactations();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMateDeletedEvent(MateDeletedEvent event) {
        if (!isViewAttached()) {
            return;
        }

        getView().refreshMates();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLactationDeletedEvent(LactationDeletedEvent event) {
        if (!isViewAttached()) {
            return;
        }

        getView().refreshLactations();
    }

    public void deleteMate(final Context context, final int position) {
        mDeleteHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    DaoSession daoSession = getModuleWrapper().getDbManager().getDaoSession(context);
                    getModuleWrapper().getDbCache().deleteMate(daoSession, mCow.getMates().get(position));
                } catch (Exception ex) {
                    Log.e(TAG, "unable to delete mate", ex);
                    getModuleWrapper().getEventBus().post(new MateDeletedEvent(StatusEvent.Status.FAILURE));
                }
                getModuleWrapper().getEventBus().post(new MateDeletedEvent(StatusEvent.Status.SUCCESS));
            }
        });
    }

    public void deleteLactation(final Context context, final int position) {
        mDeleteHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    DaoSession daoSession = getModuleWrapper().getDbManager().getDaoSession(context);
                    getModuleWrapper().getDbCache().deleteLactation(daoSession, mCow.getLactations().get(position));
                } catch (Exception ex) {
                    Log.e(TAG, "unable to delete lactation", ex);
                    getModuleWrapper().getEventBus().post(new LactationDeletedEvent(StatusEvent.Status.FAILURE));
                }
                getModuleWrapper().getEventBus().post(new LactationDeletedEvent(StatusEvent.Status.SUCCESS));
            }
        });
    }
}
