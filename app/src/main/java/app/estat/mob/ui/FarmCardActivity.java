package app.estat.mob.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseActivity;
import app.estat.mob.mvp.presenter.FarmCardActivityPresenter;
import app.estat.mob.mvp.util.ViewUtils;
import app.estat.mob.mvp.view.FarmCardActivityView;
import butterknife.BindView;

public class FarmCardActivity extends MvpBaseActivity<FarmCardActivityPresenter,
        FarmCardActivityView> implements FarmCardActivityView {
    private final static String ICON_LOGO_KEY = "app.estat.mob.ui.FarmCardActivity.ICON_LOGO_KEY";

    private final static String TOOLBAR_TEXT_KEY = "app.estat.mob.ui.FarmCardActivity.TOOLBAR_TEXT_KEY";

    @BindView(R.id.activity_app_bar_logo)
    ImageView mAppBarLogo;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_farm_card;
    }

    public static Intent newIntent(@NonNull Context context, String iconRes, String nameRes) {
        Intent intent = new Intent(context, FarmCardActivity.class);
        intent.putExtra(ICON_LOGO_KEY, ViewUtils.getResId(context, iconRes));
        intent.putExtra(TOOLBAR_TEXT_KEY, ViewUtils.getResId(context, nameRes));
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        setActionBarTittle((Integer) bundle.get(TOOLBAR_TEXT_KEY));
        mAppBarLogo.setImageDrawable(getResources().getDrawable((Integer) bundle.get(ICON_LOGO_KEY)));

        addFragment(R.id.activity_farm_card_container, FarmCardFragment.newInstance(), false);
    }

    @NonNull
    @Override
    public FarmCardActivityPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new FarmCardActivityPresenter(applicationComponent);
    }
}
