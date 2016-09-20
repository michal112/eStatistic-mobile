package app.estat.mob.ui.module;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.module.FarmCardFragmentPresenter;
import app.estat.mob.mvp.util.ViewUtils;
import app.estat.mob.mvp.view.module.FarmCardFragmentView;
import butterknife.BindView;
import butterknife.OnClick;

public class FarmCardFragment extends MvpBaseFragment<FarmCardFragmentPresenter, FarmCardFragmentView>
        implements FarmCardFragmentView {
    private static final String TAG = FarmCardFragment.class.getName();

    private static final String USER_IMAGE_URI_KEY = "app.estat.mob.ui.module.USER_IMAGE_URI_KEY";

    public static int REQUEST_USER_IMAGE_CAPTURE = 1;

    @BindView(R.id.fragment_farm_card_user_image_progress)
    ProgressBar mUserImageProgress;

    @BindView(R.id.fragment_farm_card_user_image)
    ImageView mUserImage;

    @BindView(R.id.fragment_farm_card_user_image_button)
    Button mUserButton;

    private PopupMenu mUserPopupMenu;

    private PopupMenu mUserPhotoPopupMenu;

    private Uri mUserImageUri;

    public static FarmCardFragment newInstance(Uri userImageUri) {
        Bundle args = new Bundle();
        args.putParcelable(USER_IMAGE_URI_KEY, userImageUri);
        FarmCardFragment fragment = new FarmCardFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @OnClick(R.id.fragment_farm_card_user_image_button)
    public void onClick() {
        mUserPopupMenu.show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserImageUri = getArguments().getParcelable(USER_IMAGE_URI_KEY);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initPopups();

        requestUserImage();
    }

    private void initPopups() {
        mUserPopupMenu = new PopupMenu(getActivity(), mUserButton);
        mUserPopupMenu.inflate(R.menu.fragment_farm_card_context_menu);
        mUserPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.fragment_farm_card_photo_menu:
                        takePhoto();
                        break;
                    case R.id.fragment_farm_card_choose_menu:
                        break;
                    default:
                        Log.d(TAG, "Unknown item selected");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void refreshUserImage() {
        hideProgress(mUserImage, mUserImageProgress);
        requestUserImage();
    }

    private void hideProgress(ImageView imageView, ProgressBar imageProgress) {
        imageView.setAlpha(0.6f);
        imageProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showUserImageProgress() {
        showProgress(mUserImage, mUserImageProgress);
    }

    private void showProgress(ImageView imageView, ProgressBar imageProgress) {
        imageView.setAlpha(0.6f);
        imageProgress.setVisibility(View.VISIBLE);
    }


    protected void requestUserImage() {
        if (getPresenter().isUserImageExists(getActivity())) {
            ViewUtils.insertImage(getActivity(), getPresenter().getUserImageUri(getActivity()),
                    R.drawable.fragment_farm_card_form_user_image, mUserImage);
        }
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mUserImageUri);
            getActivity().startActivityForResult(intent, REQUEST_USER_IMAGE_CAPTURE);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_farm_card;
    }

    @NonNull
    @Override
    public FarmCardFragmentPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new FarmCardFragmentPresenter(applicationComponent);
    }
}
