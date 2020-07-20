package app.estat.mob.mvp.presenter.action;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.dao.DaoSession;
import app.estat.mob.event.BullSavedEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.model.BullData;
import app.estat.mob.mvp.util.PresenterUtils;
import app.estat.mob.mvp.view.action.AddBullFragmentView;

public class AddBullFragmentPresenter extends MvpBaseFragmentPresenter<AddBullFragmentView> {

    private static final String TAG = AddBullFragmentPresenter.class.getName();

    private PresenterUtils.PresenterHandler mBullHandlerThread;

    public AddBullFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

        mBullHandlerThread = new PresenterUtils.PresenterHandler(AddBullFragmentPresenter.class.getName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBullSavedEvent(BullSavedEvent event) {
        if (!isViewAttached()) {
            return;
        }

        getView().setActivityResult(event.getStatus());
    }

    public void saveBullData(final Context context, final BullData bullData) {
        mBullHandlerThread.post(new Runnable() {
            @Override
            public void run() {
                try {
                    DaoSession daoSession = getModuleWrapper().getDbManager().getDaoSession(context);
                    getModuleWrapper().getDbCache().saveBull(daoSession, bullData.getBull());
                } catch (Exception ex) {
                    Log.e(TAG, "unable to save bull", ex);
                    getModuleWrapper().getEventBus().post(new BullSavedEvent(BullSavedEvent.Status.FAILURE));
                }
                getModuleWrapper().getEventBus().post(new BullSavedEvent(BullSavedEvent.Status.SUCCESS));
            }
        });
    }
}
