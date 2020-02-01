package app.estat.mob.mvp.presenter.action;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Cow;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.view.action.ViewCowFragmentView;

public class ViewCowFragmentPresenter extends MvpBaseFragmentPresenter<ViewCowFragmentView> {

    private String mCowPublicId;

    private Cow mCow;

    public ViewCowFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }

    public Cow getCow() {
        return mCow;
    }

    public void setCowPublicId(String cowPublicId) {
        mCowPublicId = cowPublicId;

        mCow = getModuleWrapper().getDbCache().findCowByPublicId(cowPublicId);
    }
}
