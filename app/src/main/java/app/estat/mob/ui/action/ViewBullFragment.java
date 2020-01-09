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
import app.estat.mob.db.entity.Bull;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.action.ViewBullFragmentPresenter;
import app.estat.mob.mvp.view.action.ViewBullFragmentView;
import app.estat.mob.ui.factory.ComponentFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.com.app.comp.view.card.FormCardView;
import pl.com.app.comp.view.text.FormTextView;

public class ViewBullFragment extends MvpBaseFragment<ViewBullFragmentPresenter, ViewBullFragmentView>
        implements ViewBullFragmentView {

    private static final String BULL_KEY = "app.estat.mob.ui.action.ViewBullFragment.BULL_KEY";

    @BindView(R.id.fragment_view_bull_basic_data)
    FormCardView mBasicData;

    FormTextView mNameComponent;

    FormTextView mNumberComponent;

    public static ViewBullFragment newInstance(String bullPublicId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BULL_KEY, bullPublicId);
        ViewBullFragment fragment = new ViewBullFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_bull, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    private void addComponents() {
        Bull bull = getPresenter().getBull();

        mNameComponent = ComponentFactory.getFormTextViewComponent(getContext(), R.drawable.ic_name, R.string.fragment_view_cow_name_hint, bull.getName());
        mNumberComponent = ComponentFactory.getFormTextViewComponent(getContext(), R.drawable.ic_number, R.string.fragment_view_cow_number_hint, bull.getNumber());

        mBasicData.insertView(mNameComponent);
        mBasicData.insertView(mNumberComponent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        getPresenter().setBullPublicId(bundle.getString(BULL_KEY));

        addComponents();
    }

    @NonNull
    @Override
    public ViewBullFragmentPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new ViewBullFragmentPresenter(context, applicationComponent);
    }
}
