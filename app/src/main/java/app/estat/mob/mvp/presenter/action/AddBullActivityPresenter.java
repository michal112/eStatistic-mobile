package app.estat.mob.mvp.presenter.action;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.view.action.AddBullActivityView;

public class AddBullActivityPresenter extends ActionActivityPresenter<AddBullActivityView> {
    public AddBullActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }
}
