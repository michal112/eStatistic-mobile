package app.estat.mob.ui.action;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.estat.mob.R;
import app.estat.mob.comp.card.FormCardView;
import app.estat.mob.comp.recycler.FormAdapter;
import app.estat.mob.comp.recycler.FormRecyclerView;
import app.estat.mob.comp.text.FormTextView;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Bull;
import app.estat.mob.db.entity.Mate;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.action.ViewBullFragmentPresenter;
import app.estat.mob.mvp.view.action.ViewBullFragmentView;
import app.estat.mob.ui.factory.ComponentFactory;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewBullFragment extends MvpBaseFragment<ViewBullFragmentPresenter, ViewBullFragmentView>
        implements ViewBullFragmentView {

    private static final String BULL_KEY = "app.estat.mob.ui.action.ViewBullFragment.BULL_KEY";

    @BindView(R.id.fragment_view_bull_basic_data)
    FormCardView mBasicData;

    FormTextView mNameComponent;

    FormTextView mNumberComponent;

    @BindView(R.id.fragment_view_bull_mates)
    FormCardView mMates;

    FormRecyclerView mMateFormRecyclerView;

    private final FormAdapter.FormItemClickListener
            mMateFormItemClickListener = new FormAdapter.FormItemClickListener() {
        @Override
        public void onClick(int position) {
            getPresenter().deleteMate(getActivity(), position);
        }
    };

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

        mNameComponent = ComponentFactory.getFormTextViewComponent(getContext(), R.drawable.ic_name, R.string.fragment_view_bull_name_hint, bull.getName());
        mNumberComponent = ComponentFactory.getFormTextViewComponent(getContext(), R.drawable.ic_number, R.string.fragment_view_bull_number_hint, bull.getNumber());

        mBasicData.insertView(mNameComponent);
        mBasicData.insertView(mNumberComponent);

        getPresenter().requestMates();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        getPresenter().setBull(bundle.getString(BULL_KEY));

        addComponents();
    }

    @NonNull
    @Override
    public ViewBullFragmentPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new ViewBullFragmentPresenter(context, applicationComponent);
    }

    @Override
    public void showMates(List<Mate> mates) {
        show(mMates, !mates.isEmpty());
        mMateFormRecyclerView = ComponentFactory.getFormRecyclerViewComponent(getContext(), FormRecyclerView.Type.COW,
                R.drawable.ic_calendar, R.drawable.ic_cow, R.drawable.ic_delete_b);

        FormAdapter formAdapter = new FormAdapter(mates, mMateFormItemClickListener);
        mMateFormRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMateFormRecyclerView.setAdapter(formAdapter);
        mMates.insertView(mMateFormRecyclerView);
    }

    @Override
    public void refreshMates() {
        show(mMates, !getPresenter().getBull().getMates().isEmpty());
        mMateFormRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void show(FormCardView formCardView, boolean show) {
        formCardView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
