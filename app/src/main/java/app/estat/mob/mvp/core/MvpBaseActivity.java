package app.estat.mob.mvp.core;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import app.estat.mob.ApplicationCore;
import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.util.ViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class MvpBaseActivity<P extends MvpBaseActivityPresenter<V>, V extends MvpBaseActivityView>
        extends AppCompatActivity implements MvpBaseActivityView {
    @BindView(R.id.activity_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.activity_navigation_view)
    NavigationView mNavigationView;

    ImageView mUserImage;

    private P presenter;

    @NonNull
    public P getPresenter() {
        return presenter;
    }

    @LayoutRes
    public abstract int getLayoutResId();

    @NonNull
    public abstract P createPresenter(ApplicationComponent applicationComponent);

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        View headerView = mNavigationView.getHeaderView(0);
        mUserImage = ButterKnife.findById(headerView, R.id.drawer_user_avatar);

        setSupportActionBar(mToolbar);
        displayActionBarTittle(false);

        presenter = createPresenter(((ApplicationCore) getApplication()).getApplicationComponent());
        presenter.attachView((V) this);
        requestUserImage();
    }

    @Override
    public void refreshUserImage() {
        requestUserImage();
    }

    protected void requestUserImage() {
        if (presenter.isUserImageExists(this)) {
            ViewUtils.insertImage(this, presenter.getUserImageUri(this),
                    R.drawable.ic_account_circle, mUserImage);
        }
    }

    public void displayActionBarTittle(boolean show) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(show);
        }
    }

    protected void setActionBarTittle(@StringRes int tittle) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(tittle);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detachView();
    }

    public Toolbar getMainToolbar() {
        return mToolbar;
    }

    @Override
    public void addFragment(int container, Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(container, fragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }
}
