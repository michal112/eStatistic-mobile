package app.estat.mob.ui.module;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.module.AddCowFragmentPresenter;
import app.estat.mob.mvp.view.module.AddCowFragmentView;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.com.app.comp.view.text.FormEditText;

public class AddCowFragment extends MvpBaseFragment<AddCowFragmentPresenter, AddCowFragmentView> implements AddCowFragmentView {

    @BindView(R.id.fragment_add_cow_container)
    LinearLayout mContainer;

    public static AddCowFragment newInstance() {
        return new AddCowFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_cow, container, false);
        ButterKnife.bind(this, view);
        addComponents();

        return view;
    }

    private void addComponents() {
        addFormEditTextComponent(mContainer, R.drawable.ic_name, R.string.fragment_add_cow_name_hint);
        addFormEditTextComponent(mContainer, R.drawable.ic_number, R.string.fragment_add_cow_number_hint);
    }

    private void addFormEditTextComponent(ViewGroup container, @DrawableRes int iconRes, @StringRes int hintRes) {
        FormEditText mComponent = new FormEditText(getContext());
        mComponent.setHint(hintRes);
        mComponent.setIcon(iconRes);

        container.addView(mComponent, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @NonNull
    @Override
    public AddCowFragmentPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new AddCowFragmentPresenter(context, applicationComponent);
    }
}
