package app.estat.mob.mvp.presenter.action;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.view.action.AddCowActivityView;

public class AddCowActivityPresenter extends ActionActivityPresenter<AddCowActivityView> {
    public AddCowActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }
}
