package app.estat.mob.ui.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import app.estat.mob.R;
import app.estat.mob.comp.card.FormCardView;
import app.estat.mob.comp.photo.FormPhotoView;
import app.estat.mob.comp.text.FormEditText;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.event.StatusEvent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.model.FarmData;
import app.estat.mob.mvp.model.manager.ImageManager;
import app.estat.mob.mvp.presenter.module.FarmCardFragmentPresenter;
import app.estat.mob.mvp.util.ActivityUtils;
import app.estat.mob.mvp.util.ViewUtils;
import app.estat.mob.mvp.view.module.FarmCardFragmentView;
import app.estat.mob.ui.factory.ComponentFactory;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FarmCardFragment extends MvpBaseFragment<FarmCardFragmentPresenter, FarmCardFragmentView>
        implements FarmCardFragmentView {
    private static final String TAG = FarmCardFragment.class.getName();

    private static final int REQUEST_USER_IMAGE_CAPTURE = 0;

    private static final int REQUEST_FARM_IMAGE_CAPTURE = 1;

    @BindView(R.id.fragment_farm_card_user_data)
    FormCardView mUserDataContainer;

    @BindView(R.id.fragment_farm_card_farm_data)
    FormCardView mFarmDataContainer;

    FormEditText mUserNameComponent;

    FormEditText mFarmAddressComponent;

    FormEditText mBarnNumberComponent;

    FormPhotoView mUserPhotoComponent;

    FormPhotoView mFarmPhotoComponent;

    public static FarmCardFragment newInstance() {
        return new FarmCardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_farm_card, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        addComponents();

        requestImage(ImageManager.USER_IMAGE);
        requestImage(ImageManager.FARM_IMAGE);

        initPopups();
        initFarmData();

        return view;
    }

    private void addComponents() {
        mUserNameComponent = ComponentFactory.getFormEditTextComponent(getContext(), R.drawable.ic_person, R.string.fragment_farm_card_user_name);
        mBarnNumberComponent = ComponentFactory.getFormEditTextComponent(getContext(), R.drawable.ic_home, R.string.fragment_farm_card_farm_number);
        mFarmAddressComponent = ComponentFactory.getFormEditTextComponent(getContext(), R.drawable.ic_location, R.string.fragment_farm_card_address_hint);
        mUserPhotoComponent = ComponentFactory.getFormPhotoViewComponent(getContext(), R.drawable.ic_camera, R.drawable.fragment_farm_card_form_user_image);
        mFarmPhotoComponent = ComponentFactory.getFormPhotoViewComponent(getContext(), R.drawable.ic_camera, R.drawable.fragment_farm_card_form_farm_image);

        mUserDataContainer.insertView(mUserNameComponent);
        mUserDataContainer.insertView(mUserPhotoComponent);

        mFarmDataContainer.insertView(mBarnNumberComponent);
        mFarmDataContainer.insertView(mFarmAddressComponent);
        mFarmDataContainer.insertView(mFarmPhotoComponent);
    }

    private void initFarmData() {
        mBarnNumberComponent.setText(getPresenter().getBarnNumber());
        mFarmAddressComponent.setText(getPresenter().getFarmAddress());
        mUserNameComponent.setText(getPresenter().getUserName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_farm_card_menu_done:
                FarmData farmData = new FarmData.Builder()
                        .userName(mUserNameComponent.getText())
                        .barnNumber(mBarnNumberComponent.getText())
                        .farmAddress(mFarmAddressComponent.getText())
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
        mUserPhotoComponent.setClickListener(new FormPhotoView.OnPopupClickListener() {
            @Override
            public void onClick() {
                takePhoto(ImageManager.USER_IMAGE);
            }
        });
        mFarmPhotoComponent.setClickListener(new FormPhotoView.OnPopupClickListener() {
            @Override
            public void onClick() {
                takePhoto(ImageManager.FARM_IMAGE);
            }
        });
    }

    private void takePhoto(int imageType) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            Uri imageTempUri = getPresenter().createImageTemp(getActivity(), imageType);
            if (imageTempUri == null) {
                return;
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageTempUri);
            if (ImageManager.USER_IMAGE == imageType) {
                startActivityForResult(intent, REQUEST_USER_IMAGE_CAPTURE);
            } else if (ImageManager.FARM_IMAGE == imageType) {
                startActivityForResult(intent, REQUEST_FARM_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    public void showImage(int imageType, Uri imageUri) {
        if (imageType == ImageManager.USER_IMAGE) {
            ViewUtils.insertImage(getActivity(), imageUri,
                    R.drawable.fragment_farm_card_form_user_image, mUserPhotoComponent);
        } else if (imageType == ImageManager.FARM_IMAGE) {
            ViewUtils.insertImage(getActivity(), imageUri,
                    R.drawable.fragment_farm_card_form_farm_image, mFarmPhotoComponent);
        }
    }

    @Override
    public void setActivityResult(StatusEvent.Status status) {
        if (status == StatusEvent.Status.SUCCESS) {
            getActivity().setResult(ActivityUtils.RESULT_FARM_CARD_SAVED);
        } else if (status == StatusEvent.Status.FAILURE) {
            getActivity().setResult(ActivityUtils.RESULT_FARM_CARD_SAVE_ERROR);
        }
        getActivity().supportFinishAfterTransition();
    }

    private void requestImage(int imageType) {
        if (imageType == ImageManager.USER_IMAGE && getPresenter().isUserImageExists()) {
            ViewUtils.insertImage(getActivity(), getPresenter().getUserImageUri(),
                    R.drawable.fragment_farm_card_form_user_image, mUserPhotoComponent);
        } else if (imageType == ImageManager.FARM_IMAGE && getPresenter().isFarmImageExists()) {
            ViewUtils.insertImage(getActivity(), getPresenter().getFarmImageUri(),
                    R.drawable.fragment_farm_card_form_farm_image, mFarmPhotoComponent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_USER_IMAGE_CAPTURE:
                if (resultCode == Activity.RESULT_OK) {
                    mUserPhotoComponent.showProgress(true);
                    getPresenter().scaleImageTemp(getActivity(), ImageManager.USER_IMAGE);
                }
                break;
            case REQUEST_FARM_IMAGE_CAPTURE:
                if (resultCode == Activity.RESULT_OK) {
                    mFarmPhotoComponent.showProgress(true);
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
