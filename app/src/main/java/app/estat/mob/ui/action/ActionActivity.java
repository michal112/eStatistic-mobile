package app.estat.mob.ui.action;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import app.estat.mob.R;
import app.estat.mob.mvp.core.MvpBaseActivity;
import app.estat.mob.mvp.presenter.action.ActionActivityPresenter;
import app.estat.mob.mvp.view.action.ActionActivityView;

public abstract class ActionActivity<P extends ActionActivityPresenter<V>, V extends ActionActivityView> extends MvpBaseActivity<P, V>
        implements ActionActivityView {

    public static Intent newIntent(@NonNull Context context, Class clazz) {
        return new Intent(context, clazz);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getTitleRes());
    }

    protected abstract int getTitleRes();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(getMenuResId(), menu);
        return true;
    }

    protected abstract int getMenuResId();

    @Override
    public int getLayoutResId() {
        return R.layout.activity_action;
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
