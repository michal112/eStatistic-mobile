package app.estat.mob.ui.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.view.MenuItem;
import android.widget.ImageView;

import app.estat.mob.R;
import app.estat.mob.mvp.core.MvpBaseActivity;
import app.estat.mob.mvp.presenter.module.ModuleActivityPresenter;
import app.estat.mob.mvp.util.ViewUtils;
import app.estat.mob.mvp.view.module.ModuleActivityView;
import butterknife.BindView;

public abstract class ModuleActivity<P extends ModuleActivityPresenter<V>, V extends ModuleActivityView> extends MvpBaseActivity<P, V>
        implements ModuleActivityView {
    
    private final static String ICON_LOGO_KEY = "app.estat.mob.ui.module.ModuleActivity.ICON_LOGO_KEY";

    private final static String TOOLBAR_TEXT_KEY = "app.estat.mob.ui.module.ModuleActivity.TOOLBAR_TEXT_KEY";

    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;

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
        displayActionBarTittle(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAppBarLayout.addOnOffsetChangedListener(ViewUtils.getAppBarOffsetListener(this, mAppBarLayout));

        Bundle bundle = getIntent().getExtras();
        setActionBarTittle((Integer) bundle.get(TOOLBAR_TEXT_KEY));
        mAppBarLogo.setImageDrawable(getResources().getDrawable((Integer) bundle.get(ICON_LOGO_KEY)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            default:
                return false;
        }
    }
}
