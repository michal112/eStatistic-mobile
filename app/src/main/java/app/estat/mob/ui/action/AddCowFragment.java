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
import app.estat.mob.event.CowSavedEvent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.model.CowData;
import app.estat.mob.mvp.presenter.action.AddCowFragmentPresenter;
import app.estat.mob.mvp.util.ActivityUtil;
import app.estat.mob.mvp.view.action.AddCowFragmentView;
import app.estat.mob.ui.factory.ComponentFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.com.app.comp.view.card.FormCardView;
import pl.com.app.comp.view.picker.FormDatePicker;
import pl.com.app.comp.view.spinner.FormSpinner;
import pl.com.app.comp.view.text.FormEditText;

public class AddCowFragment extends MvpBaseFragment<AddCowFragmentPresenter, AddCowFragmentView>
        implements AddCowFragmentView {

    private static final String TAG = AddCowFragment.class.getName();

    @BindView(R.id.fragment_add_cow_basic_data)
    FormCardView mBasicData;

    FormEditText mNameComponent;

    FormEditText mNumberComponent;

    FormSpinner mBookComponent;

    FormDatePicker mBirthdayComponent;

    public static AddCowFragment newInstance() {
        return new AddCowFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_cow, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        addComponents();

        return view;
    }

    private void addComponents() {
        mNameComponent = ComponentFactory.getFormEditTextComponent(getContext(), R.drawable.ic_name, R.string.fragment_add_cow_name_hint);
        mNumberComponent = ComponentFactory.getFormEditTextComponent(getContext(), R.drawable.ic_number, R.string.fragment_add_cow_number_hint);
        mBookComponent = ComponentFactory.getFormSpinnerComponent(getContext(), R.drawable.ic_book , "R.string.fragment_add_cow_book_hint", getPresenter().getSpinnerData(), false);
        mBirthdayComponent = ComponentFactory.getFormDatePickerComponent(getActivity(), R.drawable.ic_calendar, R.string.fragment_add_cow_birthday_hint);

        mBasicData.insertView(mNameComponent);
        mBasicData.insertView(mNumberComponent);
        mBasicData.insertView(mBookComponent);
        mBasicData.insertView(mBirthdayComponent);
    }

    @NonNull
    @Override
    public AddCowFragmentPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new AddCowFragmentPresenter(context, applicationComponent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_add_cow_menu_done:
                CowData cowData = new CowData.CowDataBuilder()
                        .withName(mNameComponent.getInput())
                        .withNumber(mNumberComponent.getInput())
                        .withBook(mBookComponent.getSelectedItem())
                        .withBirthday(mBirthdayComponent.getDate())
                        .withPublicId(UUID.randomUUID().toString())
                        .build();
                getPresenter().saveCowData(getActivity(), cowData);
                break;
            default:
                Log.d(TAG, "Unknown option was clicked");
                break;
        }

        return true;
    }

    @Override
    public void setActivityResult(CowSavedEvent.Status status) {
        switch (status) {
            case SUCCESS:
                getActivity().setResult(ActivityUtil.RESULT_COW_SAVED);
                Log.d(TAG, "New cow saved successfully");
                break;
            case FAILURE:
                getActivity().setResult(ActivityUtil.RESULT_COW_SAVE_ERROR);
                Log.e(TAG, "Cow save error");
                break;
        }

        getActivity().finish();
    }
}
