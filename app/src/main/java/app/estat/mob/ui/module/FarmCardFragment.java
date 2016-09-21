package app.estat.mob.ui.module;

import android.app.Activity;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.model.FarmData;
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

    @BindView(R.id.fragment_farm_card_user_image_progress)
    ProgressBar mUserImageProgress;

    @BindView(R.id.fragment_farm_card_user_image)
    ImageView mUserImage;

    @BindView(R.id.fragment_farm_card_user_image_button)
    Button mUserButton;

    @BindView(R.id.fragment_farm_card_user_name)
    EditText mUserName;

    @BindView(R.id.fragment_farm_card_farm_image_progress)
    ProgressBar mFarmImageProgress;

    @BindView(R.id.fragment_farm_card_farm_image)
    ImageView mFarmImage;

    @BindView(R.id.fragment_farm_card_farm_image_button)
    Button mFarmButton;

    @BindView(R.id.fragment_farm_card_barn_number)
    EditText mBarnNumber;

    @BindView(R.id.fragment_farm_card_farm_address)
    EditText mFarmAddress;

    private PopupMenu mUserPopupMenu;

    private PopupMenu mUserPhotoPopupMenu;

    private PopupMenu mFarmPopupMenu;

    private PopupMenu mFarmPhotoPopupMenu;

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
                FarmData farmData = new FarmData.Builder()
                        .userName(mUserName.getText().toString())
                        .barnNumber(mBarnNumber.getText().toString())
                        .farmAddress(mFarmAddress.getText().toString())
                        .build();
                getPresenter().saveFarmData(getActivity(), farmData);
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
            ViewUtils.hideProgress(mUserImage, mUserImageProgress);
            ViewUtils.insertImage(getActivity(), imageUri,
                    R.drawable.fragment_farm_card_form_user_image, mUserImage, mUserImageProgress);
        } else if (imageType == ImageManager.FARM_IMAGE) {
            ViewUtils.hideProgress(mFarmImage, mFarmImageProgress);
            ViewUtils.insertImage(getActivity(), imageUri,
                    R.drawable.fragment_farm_card_form_farm_image, mUserImage, mUserImageProgress);
        }
    }



    @Override
    public void showImageProgress(int imageType) {
        if (imageType == ImageManager.USER_IMAGE) {
            ViewUtils.showProgress(mUserImage, mUserImageProgress);
        } else if (imageType == ImageManager.FARM_IMAGE) {
            ViewUtils.showProgress(mFarmImage, mFarmImageProgress);
        }
    }



    private void requestImage(int imageType) {
        if (imageType == ImageManager.USER_IMAGE) {
            ViewUtils.showProgress(mUserImage, mUserImageProgress);
            if (getPresenter().isUserImageExists()) {
                ViewUtils.insertImage(getActivity(), getPresenter().getUserImageUri(),
                        R.drawable.fragment_farm_card_form_user_image, mUserImage, mUserImageProgress);
            }
            ViewUtils.hideProgress(mUserImage, mUserImageProgress);
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
    public FarmCardFragmentPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new FarmCardFragmentPresenter(context, applicationComponent);
    }
}
