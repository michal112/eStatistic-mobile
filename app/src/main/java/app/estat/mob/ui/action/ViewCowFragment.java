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
import app.estat.mob.db.entity.Cow;
import app.estat.mob.db.entity.Lactation;
import app.estat.mob.db.entity.Mate;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.action.ViewCowFragmentPresenter;
import app.estat.mob.mvp.util.ViewUtils;
import app.estat.mob.mvp.view.action.ViewCowFragmentView;
import app.estat.mob.ui.factory.ComponentFactory;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewCowFragment extends MvpBaseFragment<ViewCowFragmentPresenter, ViewCowFragmentView>
        implements ViewCowFragmentView {

    private static final String COW_KEY = "app.estat.mob.ui.action.ViewCowFragment.COW_KEY";

    @BindView(R.id.fragment_view_cow_basic_data)
    FormCardView mBasicData;

    FormTextView mNameComponent;

    FormTextView mNumberComponent;

    FormTextView mBookComponent;

    FormTextView mBirthdayComponent;

    @BindView(R.id.fragment_view_cow_mates)
    FormCardView mMates;

    FormRecyclerView mMateFormRecyclerView;

    @BindView(R.id.fragment_view_cow_lactations)
    FormCardView mLactations;

    FormRecyclerView mLactationFormRecyclerView;

    private final FormAdapter.FormItemClickListener
            mMateFormItemClickListener = new FormAdapter.FormItemClickListener() {
        @Override
        public void onClick(int position) {
            getPresenter().deleteMate(getActivity(), position);
        }
    };

    private final FormAdapter.FormItemClickListener
            mLactationFormItemClickListener = new FormAdapter.FormItemClickListener() {
        @Override
        public void onClick(int position) {
            getPresenter().deleteLactation(getActivity(), position);
        }
    };

    public static ViewCowFragment newInstance(String cowPublicId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(COW_KEY, cowPublicId);
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
        Cow cow = getPresenter().getCow();

        mNameComponent = ComponentFactory.getFormTextViewComponent(getContext(), R.drawable.ic_name, R.string.fragment_view_cow_name_hint, cow.getName());
        mNumberComponent = ComponentFactory.getFormTextViewComponent(getContext(), R.drawable.ic_number, R.string.fragment_view_cow_number_hint, cow.getNumber());
        mBookComponent = ComponentFactory.getFormTextViewComponent(getContext(), R.drawable.ic_book, R.string.fragment_view_cow_book_hint,
                getActivity().getString(ViewUtils.getResId(getActivity(), cow.getBook().getTitle())));
        mBirthdayComponent = ComponentFactory.getFormTextViewComponent(getContext(), R.drawable.ic_calendar, R.string.fragment_view_cow_birthday_hint, ViewUtils.format(cow.getBirthday()));

        mBasicData.insertView(mNameComponent);
        mBasicData.insertView(mNumberComponent);
        mBasicData.insertView(mBookComponent);
        mBasicData.insertView(mBirthdayComponent);

        getPresenter().requestMates();
        getPresenter().requestLactations();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        getPresenter().setCow(bundle.getString(COW_KEY));

        addComponents();
    }

    @NonNull
    @Override
    public ViewCowFragmentPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new ViewCowFragmentPresenter(context, applicationComponent);
    }

    @Override
    public void showMates(List<Mate> mates) {
        show(mMates, !mates.isEmpty());
        mMateFormRecyclerView = ComponentFactory.getFormRecyclerViewComponent(getContext(), FormRecyclerView.Type.BULL,
                R.drawable.ic_calendar, R.drawable.ic_bull, R.drawable.ic_delete_b);

        FormAdapter formAdapter = new FormAdapter(mates, mMateFormItemClickListener);
        mMateFormRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMateFormRecyclerView.setAdapter(formAdapter);
        mMates.insertView(mMateFormRecyclerView);
    }

    @Override
    public void showLactations(List<Lactation> lactations) {
        show(mLactations, !lactations.isEmpty());
        mLactationFormRecyclerView = ComponentFactory.getFormRecyclerViewComponent(getContext(), FormRecyclerView.Type.LACTATION,
                R.drawable.ic_calendar, R.drawable.ic_number, R.drawable.ic_delete_b);

        FormAdapter formAdapter = new FormAdapter(lactations, mLactationFormItemClickListener);
        mLactationFormRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLactationFormRecyclerView.setAdapter(formAdapter);
        mLactations.insertView(mLactationFormRecyclerView);
    }

    @Override
    public void refreshMates() {
        show(mMates, !getPresenter().getCow().getMates().isEmpty());
        mMateFormRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void refreshLactations() {
        show(mLactations, !getPresenter().getCow().getLactations().isEmpty());
        mLactationFormRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void show(FormCardView formCardView, boolean show) {
        formCardView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
