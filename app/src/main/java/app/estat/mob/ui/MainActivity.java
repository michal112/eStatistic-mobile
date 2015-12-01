package app.estat.mob.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.mvp.core.MvpBaseActivity;
import app.estat.mob.mvp.presenter.MainPresenter;
import app.estat.mob.mvp.view.MainView;

public class MainActivity extends MvpBaseActivity<MainPresenter, MainView> implements MainView {

    @Override
    public int getLayoutResId() {
        return R.layout.main_activity;
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
