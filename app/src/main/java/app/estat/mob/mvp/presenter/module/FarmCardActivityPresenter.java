package app.estat.mob.mvp.presenter.module;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.view.module.FarmCardActivityView;

public class FarmCardActivityPresenter extends ModuleActivityPresenter<FarmCardActivityView>  {
    public FarmCardActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }
}
