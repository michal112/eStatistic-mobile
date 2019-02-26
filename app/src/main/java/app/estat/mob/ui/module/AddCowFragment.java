package app.estat.mob.ui.module;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.type.FormSpinnerItem;
import app.estat.mob.event.CowSavedEvent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.model.CowData;
import app.estat.mob.mvp.presenter.module.AddCowFragmentPresenter;
import app.estat.mob.mvp.util.ActivityUtil;
import app.estat.mob.mvp.view.module.AddCowFragmentView;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.com.app.comp.view.card.FormCardView;
import pl.com.app.comp.view.picker.FormDatePicker;
import pl.com.app.comp.view.spinner.FormSpinner;
import pl.com.app.comp.view.spinner.FormSpinnerAdapter;
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
        mNameComponent = getFormEditTextComponent(R.drawable.ic_name, R.string.fragment_add_cow_name_hint);
        mNumberComponent = getFormEditTextComponent(R.drawable.ic_number, R.string.fragment_add_cow_number_hint);
        mBookComponent = getFormBookSpinner(R.drawable.ic_book , "R.string.fragment_add_cow_book_hint");
        mBirthdayComponent = getFormBirthdayComponent(R.drawable.ic_calendar, R.string.fragment_add_cow_birthday_hint);

        mBasicData.insertView(mNameComponent);
        mBasicData.insertView(mNumberComponent);
        mBasicData.insertView(mBookComponent);
        mBasicData.insertView(mBirthdayComponent);
    }

    private FormEditText getFormEditTextComponent(@DrawableRes int iconRes, @StringRes int hintRes) {
        FormEditText component = new FormEditText(getContext());
        component.setHint(hintRes);
        component.setIcon(iconRes);
        return component;
    }

    private FormSpinner getFormBookSpinner(@DrawableRes int iconRes, String hintRes) {
        FormSpinner component = new FormSpinner(getContext());
        component.setIcon(iconRes);

        List<FormSpinnerItem> data = getPresenter().getSpinnerData();
        FormSpinnerAdapter adapter = new FormSpinnerAdapter(getContext(), data, hintRes);
        component.getSpinner().setAdapter(adapter);
        component.getSpinner().setSelection(adapter.getCount());

        return component;
    }

    private FormDatePicker getFormBirthdayComponent(@DrawableRes int iconRes, @StringRes int hintRes) {
        FormDatePicker component = new FormDatePicker(getContext());
        component.setHint(hintRes);
        component.setIcon(iconRes);
        component.setFragmentManager(getActivity().getSupportFragmentManager());
        return component;
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
