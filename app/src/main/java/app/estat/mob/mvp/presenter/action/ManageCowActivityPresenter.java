package app.estat.mob.mvp.presenter.action;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;

public class ManageCowActivityPresenter extends ActionActivityPresenter<ManageCowActivityView> {

    public void deleteCow(String publicId) {

    }

    private static final String TAG = ManageCowActivityPresenter.class.getName();

    private DeleteCowHandlerThread mCowHandlerThread;

    public ManageCowActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

        mCowHandlerThread = new DeleteCowHandlerThread();
        mCowHandlerThread.start();
        mCowHandlerThread.getLooper();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCowDeletedEvent(CowDeletedEvent event) {
        if (!isViewAttached()) {
            return;
        }

        getView().s.setActivityResult(event.getStatus());
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
