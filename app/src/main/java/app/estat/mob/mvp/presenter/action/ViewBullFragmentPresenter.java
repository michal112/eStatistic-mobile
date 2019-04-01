package app.estat.mob.mvp.presenter.action;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Bull;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.view.action.ViewBullFragmentView;

public class ViewBullFragmentPresenter extends MvpBaseFragmentPresenter<ViewBullFragmentView> {

    private String mBullPublicId;

    private Bull mBull;

    public ViewBullFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }

    public Bull getBull() {
        return mBull;
    }

    public void setBullPublicId(String bullPublicId) {
        mBullPublicId = bullPublicId;

        mBull = getModuleWrapper().getDbCache().findBullByPublicId(mBullPublicId);
    }
}
