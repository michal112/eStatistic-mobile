package app.estat.mob.mvp.presenter.module;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.view.module.AddCowFragmentView;

public class AddCowFragmentPresenter extends MvpBaseFragmentPresenter<AddCowFragmentView> {
    public AddCowFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }
}
