package app.estat.mob.ui.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseActivity;
import app.estat.mob.mvp.presenter.module.AddCowActivityPresenter;
import app.estat.mob.mvp.util.ActivityUtil;
import app.estat.mob.mvp.view.module.AddCowActivityView;
import app.estat.mob.ui.dashboard.DashboardFragment;

public class AddCowActivity extends MvpBaseActivity<AddCowActivityPresenter, AddCowActivityView>
        implements AddCowActivityView {

    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, AddCowActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addFragment(R.id.activity_add_cow_container, AddCowFragment.newInstance(), false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_cow_menu, menu);
        return true;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_add_cow;
    }

    @NonNull
    @Override
    public AddCowActivityPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new AddCowActivityPresenter(context, applicationComponent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return false;
        }
    }
}
