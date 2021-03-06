package app.estat.mob.mvp.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    ImageView mFarmPhoto;

    ProgressBar mFarmPhotoProgress;

    ImageView mUserImage;

    ProgressBar mUserImageProgress;

    TextView mUserName;

    TextView mFarmAddress;

    TextView mBarnNumber;

    private P presenter;

    @NonNull
    public P getPresenter() {
        return presenter;
    }

    @LayoutRes
    public abstract int getLayoutResId();

    @NonNull
    public abstract P createPresenter(Context context, ApplicationComponent applicationComponent);

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);

        View headerView = mNavigationView.getHeaderView(0);
        mUserImage = ButterKnife.findById(headerView, R.id.drawer_user_avatar);
        mUserImageProgress = ButterKnife.findById(headerView, R.id.drawer_user_progress);
        mUserName = ButterKnife.findById(headerView, R.id.drawer_user_name);
        mFarmAddress = ButterKnife.findById(headerView, R.id.drawer_farm_address);
        mFarmPhoto = ButterKnife.findById(headerView, R.id.drawer_farm_photo);
        mFarmPhotoProgress = ButterKnife.findById(headerView, R.id.drawer_farm_photo_progress);
        mBarnNumber = ButterKnife.findById(headerView, R.id.drawer_barn_number);

        setSupportActionBar(mToolbar);

        presenter = createPresenter(this, ((ApplicationCore) getApplication()).getApplicationComponent());
        presenter.attachView((V) this);

        refreshDrawerData();
    }

    @Override
    public void showMessage(@StringRes int resId) {
         Snackbar snackbar = Snackbar.make(getWindow()
                 .getDecorView().findViewById(android.R.id.content), resId, Snackbar.LENGTH_LONG);
         snackbar.show();
    }

    public void refreshDrawerData() {
        requestUserImage();
        requestFarmImage();

        String userName = getPresenter().getUserName();
        if (userName.isEmpty()) {
            mUserName.setText(R.string.drawer_user_unknown);
        } else {
            mUserName.setText(userName);
        }
        showOrHideField(getPresenter().getFarmAddress(), mFarmAddress);
        showOrHideField(getPresenter().getBarnNumber(), mBarnNumber);
    }

    private void showOrHideField(String value, TextView view) {
        if (!value.isEmpty()) {
            view.setText(value);
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    private void requestUserImage() {
        ViewUtils.showProgress(mUserImage, mUserImageProgress);
        if (presenter.isUserImageExists()) {
            ViewUtils.insertImage(this, presenter.getUserImageUri(),
                    R.drawable.ic_account_circle, mUserImage, mUserImageProgress);
        } else {
            ViewUtils.hideProgress(mUserImage, mUserImageProgress);
        }
    }

    private void requestFarmImage() {
        ViewUtils.showProgress(mFarmPhoto, mFarmPhotoProgress);
        if (presenter.isFarmImageExists()) {
            ViewUtils.insertImage(this, presenter.getFarmImageUri(),
                    R.drawable.farm_photo, mFarmPhoto, mFarmPhotoProgress);
        } else {
            ViewUtils.hideProgress(mFarmPhoto, mFarmPhotoProgress);
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

    protected Toolbar getMainToolbar() {
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
