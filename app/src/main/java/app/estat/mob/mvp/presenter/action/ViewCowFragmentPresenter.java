package app.estat.mob.mvp.presenter.action;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.model.CowData;
import app.estat.mob.mvp.view.action.ViewCowFragmentView;

public class ViewCowFragmentPresenter extends MvpBaseFragmentPresenter<ViewCowFragmentView> {

    private CowData mCowData;

    public ViewCowFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }

    public void setCowData(CowData cowData) {
        mCowData = cowData;
    }

    public CowData getCowData() {
        return mCowData;
    }
}
