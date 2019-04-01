package app.estat.mob.ui.action;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.event.BullSavedEvent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.model.BullData;
import app.estat.mob.mvp.presenter.action.AddBullFragmentPresenter;
import app.estat.mob.mvp.util.ActivityUtils;
import app.estat.mob.mvp.view.action.AddBullFragmentView;
import app.estat.mob.ui.factory.ComponentFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.com.app.comp.view.card.FormCardView;
import pl.com.app.comp.view.text.FormEditText;

public class AddBullFragment extends MvpBaseFragment<AddBullFragmentPresenter, AddBullFragmentView>
        implements AddBullFragmentView {

    private static final String TAG = AddBullFragment.class.getName();

    @BindView(R.id.fragment_add_bull_basic_data)
    FormCardView mBasicData;

    FormEditText mNameComponent;

    FormEditText mNumberComponent;

    public static AddBullFragment newInstance() {
        return new AddBullFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_bull, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        addComponents();

        return view;
    }

    private void addComponents() {
        mNameComponent = ComponentFactory.getFormEditTextComponent(getContext(), R.drawable.ic_name, R.string.fragment_add_bull_name_hint);
        mNumberComponent = ComponentFactory.getFormEditTextComponent(getContext(), R.drawable.ic_number, R.string.fragment_add_bull_number_hint);

        mBasicData.insertView(mNameComponent);
        mBasicData.insertView(mNumberComponent);
    }

    @NonNull
    @Override
    public AddBullFragmentPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new AddBullFragmentPresenter(context, applicationComponent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_add_bull_menu_done:
                BullData bullData = new BullData.BullDataBuilder()
                        .withName(mNameComponent.getInput())
                        .withNumber(mNumberComponent.getInput())
                        .withPublicId(UUID.randomUUID().toString())
                        .build();
                getPresenter().saveBullData(getActivity(), bullData);
                break;
            default:
                Log.d(TAG, "Unknown option was clicked");
                break;
        }

        return true;
    }

    @Override
    public void setActivityResult(BullSavedEvent.Status status) {
        switch (status) {
            case SUCCESS:
                getActivity().setResult(ActivityUtils.RESULT_BULL_SAVED);
                Log.d(TAG, "New bull saved successfully");
                break;
            case FAILURE:
                getActivity().setResult(ActivityUtils.RESULT_BULL_SAVE_ERROR);
                Log.e(TAG, "Bull save error");
                break;
        }

        getActivity().finish();
    }
}
