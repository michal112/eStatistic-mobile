package app.estat.mob.ui.module;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.module.FarmCardFragmentPresenter;
import app.estat.mob.mvp.view.module.FarmCardFragmentView;
import butterknife.BindView;
import butterknife.OnClick;

public class FarmCardFragment extends MvpBaseFragment<FarmCardFragmentPresenter, FarmCardFragmentView>
        implements FarmCardFragmentView {
    private static final String TAG = FarmCardFragment.class.getName();

    public static int REQUEST_IMAGE_CAPTURE = 1;

    @BindView(R.id.fragment_farm_card_user_image_button)
    Button mButton;

    private PopupMenu mPopupMenu;

    public static FarmCardFragment newInstance() {
        return new FarmCardFragment();
    }

    @OnClick(R.id.fragment_farm_card_user_image_button)
    public void onClick() {
        mPopupMenu.show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPopupMenu = new PopupMenu(getActivity(), mButton);
        mPopupMenu.inflate(R.menu.fragment_farm_card_context_menu);
        mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.fragment_farm_card_photo_menu:
                        takePhoto();
                        break;
                    case R.id.fragment_farm_card_choose_menu:
                        //TODO choosePhoto();
                        break;
                    default:
                        Log.d(TAG, "Unknown item selected");
                        break;
                }
                return true;
            }
        });
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, getPresenter().requestUserImageUri(getActivity()));
            getActivity().startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
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
