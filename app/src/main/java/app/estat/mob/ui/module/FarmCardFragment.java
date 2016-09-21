package app.estat.mob.ui.module;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.model.ImageManager;
import app.estat.mob.mvp.presenter.module.FarmCardFragmentPresenter;
import app.estat.mob.mvp.util.ViewUtils;
import app.estat.mob.mvp.view.module.FarmCardFragmentView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FarmCardFragment extends MvpBaseFragment<FarmCardFragmentPresenter, FarmCardFragmentView>
        implements FarmCardFragmentView {
    private static final String TAG = FarmCardFragment.class.getName();

    private static final int REQUEST_USER_IMAGE_CAPTURE = 0;

    private static final int REQUEST_FARM_IMAGE_CAPTURE = 1;

    private static final float IMAGE_LOADING_ALPHA = 0.5f;

    private static final float IMAGE_LOADED_ALPHA = 1f;

    @BindView(R.id.fragment_farm_card_user_image_progress)
    ProgressBar mUserImageProgress;

    @BindView(R.id.fragment_farm_card_user_image)
    ImageView mUserImage;

    @BindView(R.id.fragment_farm_card_user_image_button)
    Button mUserButton;

    @BindView(R.id.fragment_farm_card_user_name)
    TextView mUserName;

    private PopupMenu mUserPopupMenu;

    private PopupMenu mUserPhotoPopupMenu;

    public static FarmCardFragment newInstance() {
        return new FarmCardFragment();
    }

    @OnClick(R.id.fragment_farm_card_user_image_button)
    public void onClick() {
        mUserPopupMenu.show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_farm_card, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        initPopups();
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_farm_card_menu_done:
                getPresenter().saveFarmData();
                break;
            default:
                Log.d(TAG, "Unknown option was clicked");
                break;
        }

        return true;
    }

    private void initPopups() {
        mUserPopupMenu = new PopupMenu(getActivity(), mUserButton);
        mUserPopupMenu.inflate(R.menu.fragment_farm_card_context_menu);
        mUserPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.fragment_farm_card_photo_menu:
                        takePhoto(ImageManager.USER_IMAGE);
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
    public void refreshImage(int imageType, Uri imageUri) {
        if (imageType == ImageManager.USER_IMAGE) {
            hideProgress(mUserImage, mUserImageProgress);
            ViewUtils.insertImage(getActivity(), imageUri, mUserImage);
        } else if (imageType == ImageManager.FARM_IMAGE) {

        }
    }

    private void hideProgress(ImageView imageView, ProgressBar imageProgress) {
        imageView.setAlpha(IMAGE_LOADED_ALPHA);
        imageProgress.setVisibility(View.GONE);
    }

    @Override
    public void showImageProgress(int imageType) {
        if (imageType == ImageManager.USER_IMAGE) {
            showProgress(mUserImage, mUserImageProgress);
        } else if (imageType == ImageManager.FARM_IMAGE) {

        }
    }

    private void showProgress(ImageView imageView, ProgressBar imageProgress) {
        imageView.setAlpha(IMAGE_LOADING_ALPHA);
        imageProgress.setVisibility(View.VISIBLE);
    }

    private void requestImage(int imageType) {
        if (imageType == ImageManager.USER_IMAGE) {
            showProgress(mUserImage, mUserImageProgress);
            if (getPresenter().isUserImageExists(getActivity())) {
                ViewUtils.insertImage(getActivity(), getPresenter().getUserImageUri(),
                        R.drawable.fragment_farm_card_form_user_image, mUserImage);
            }
            hideProgress(mUserImage, mUserImageProgress);
        } else if (imageType == ImageManager.FARM_IMAGE) {

        }
    }

    private void takePhoto(int imageType) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            Uri imageTempUri;
            if (imageType == ImageManager.USER_IMAGE) {
                imageTempUri = getPresenter().createUserImageTemp(getActivity());
                if (imageTempUri == null) {
                    return;
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageTempUri);
                startActivityForResult(intent, REQUEST_USER_IMAGE_CAPTURE);
            } else if (imageType == ImageManager.FARM_IMAGE) {
                imageTempUri = getPresenter().createFarmImageTemp(getActivity());
                if (imageTempUri == null) {
                    return;
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageTempUri);
                startActivityForResult(intent, REQUEST_FARM_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_USER_IMAGE_CAPTURE:
                if (resultCode == Activity.RESULT_OK) {
                    getPresenter().scaleImageTemp(getActivity(), ImageManager.USER_IMAGE);
                }
                break;
            case REQUEST_FARM_IMAGE_CAPTURE:
                if (resultCode == Activity.RESULT_OK) {
                    getPresenter().scaleImageTemp(getActivity(), ImageManager.FARM_IMAGE);
                }
                break;
            default:
                Log.d(TAG, "Unknown activity request code received");
                break;
        }
    }

    @NonNull
    @Override
    public FarmCardFragmentPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new FarmCardFragmentPresenter(applicationComponent);
    }
}
