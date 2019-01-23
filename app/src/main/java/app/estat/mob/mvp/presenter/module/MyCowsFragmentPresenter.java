package app.estat.mob.mvp.presenter.module;

import android.content.Context;

import java.util.List;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Cow;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.view.module.MyCowsFragmentView;

public class MyCowsFragmentPresenter extends MvpBaseFragmentPresenter<MyCowsFragmentView> {

    private List<Cow> mCows;

    public MyCowsFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

        mCows = getModuleWrapper().getDbCache().getCows();
    }

    public void requestCows() {
        if (!isViewAttached()) {
            return;
        }

        getView().showCows(mCows);
    }

    public Cow getCow(int position) {
        if (mCows.isEmpty()) {
            return null;
        }

        return mCows.get(position);
    }
}
