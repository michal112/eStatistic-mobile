package app.estat.mob.ui.action;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.model.CowData;
import app.estat.mob.mvp.presenter.action.ViewCowFragmentPresenter;
import app.estat.mob.mvp.util.ViewUtils;
import app.estat.mob.mvp.view.action.ViewCowFragmentView;
import app.estat.mob.ui.factory.ComponentFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.com.app.comp.view.card.FormCardView;
import pl.com.app.comp.view.text.FormTextView;

public class ViewCowFragment extends MvpBaseFragment<ViewCowFragmentPresenter, ViewCowFragmentView>
        implements ViewCowFragmentView {

    private static final String COW_KEY = "app.estat.mob.ui.action.ViewCowFragment.COW_KEY";

    @BindView(R.id.fragment_view_cow_basic_data)
    FormCardView mBasicData;

    FormTextView mNameComponent;

    FormTextView mNumberComponent;

    FormTextView mBookComponent;

    FormTextView mBirthdayComponent;

    public static ViewCowFragment newInstance(CowData cowData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(COW_KEY, cowData);
        ViewCowFragment fragment = new ViewCowFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_cow, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    private void addComponents() {
        CowData cowData = getPresenter().getCowData();

        mNameComponent = ComponentFactory.getFormTextViewComponent(getContext(), R.drawable.ic_name, R.string.fragment_view_cow_name_hint, cowData.getName());
        mNumberComponent = ComponentFactory.getFormTextViewComponent(getContext(), R.drawable.ic_number, R.string.fragment_view_cow_number_hint, cowData.getNumber());
        mBookComponent = ComponentFactory.getFormTextViewComponent(getContext(), R.drawable.ic_book, R.string.fragment_view_cow_book_hint,
                getActivity().getString(ViewUtils.getResId(getActivity(), cowData.getBook().getTitle())));
        mBirthdayComponent = ComponentFactory.getFormTextViewComponent(getContext(), R.drawable.ic_calendar, R.string.fragment_view_cow_birthday_hint, cowData.getBirthdayAsString());

        mBasicData.insertView(mNameComponent);
        mBasicData.insertView(mNumberComponent);
        mBasicData.insertView(mBookComponent);
        mBasicData.insertView(mBirthdayComponent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        getPresenter().setCowData((CowData) bundle.getSerializable(COW_KEY));

        addComponents();
    }

    @NonNull
    @Override
    public ViewCowFragmentPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new ViewCowFragmentPresenter(context, applicationComponent);
    }
}
