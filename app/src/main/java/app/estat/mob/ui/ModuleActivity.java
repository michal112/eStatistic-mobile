package app.estat.mob.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseActivity;
import app.estat.mob.mvp.presenter.ModuleActivityPresenter;
import app.estat.mob.mvp.util.ViewUtils;
import app.estat.mob.mvp.view.ModuleActivityView;
import butterknife.BindView;
import me.henrytao.smoothappbarlayout.SmoothAppBarLayout;

public abstract class ModuleActivity extends MvpBaseActivity<ModuleActivityPresenter, ModuleActivityView>
        implements ModuleActivityView {
    private final static String ICON_LOGO_KEY = "app.estat.mob.ui.ModuleActivity.ICON_LOGO_KEY";

    private final static String TOOLBAR_TEXT_KEY = "app.estat.mob.ui.ModuleActivity.TOOLBAR_TEXT_KEY";

    private static final String TAG = ModuleActivity.class.getName();

    @BindView(R.id.smooth_app_bar_layout)
    SmoothAppBarLayout mAppBarLayout;

    @BindView(R.id.activity_app_bar_logo)
    ImageView mAppBarLogo;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_module;
    }

    protected static Intent newIntent(@NonNull Context context, Class<? extends ModuleActivity> clazz,
                                   String iconRes, String nameRes) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(ICON_LOGO_KEY, ViewUtils.getResId(context, iconRes));
        intent.putExtra(TOOLBAR_TEXT_KEY, ViewUtils.getResId(context, nameRes));
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAppBarLayout.addOnOffsetChangedListener(ViewUtils.getAppBarOffsetListener(this, mAppBarLayout));

        Bundle bundle = getIntent().getExtras();
        setActionBarTittle((Integer) bundle.get(TOOLBAR_TEXT_KEY));
        mAppBarLogo.setImageDrawable(getResources().getDrawable((Integer) bundle.get(ICON_LOGO_KEY)));
    }

    @NonNull
    @Override
    public ModuleActivityPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new ModuleActivityPresenter(applicationComponent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                Log.d(TAG, "Unknown option was clicked");
                break;
        }

        return true;
    }
}
