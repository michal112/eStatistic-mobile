package app.estat.mob.ui.module;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.estat.mob.R;
import app.estat.mob.comp.recycler.AnimalAdapter;
import app.estat.mob.comp.recycler.AnimalRecyclerView;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Bull;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.module.BullsFragmentPresenter;
import app.estat.mob.mvp.view.module.BullsFragmentView;
import app.estat.mob.ui.action.ActionActivity;
import app.estat.mob.ui.action.ViewBullActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BullsFragment extends MvpBaseFragment<BullsFragmentPresenter, BullsFragmentView>
        implements BullsFragmentView {

    public static final int VIEW_BULL = 1;

    @BindView(R.id.fragment_bulls_recycler_view)
    AnimalRecyclerView mRecyclerView;

    public static BullsFragment newInstance() {
        return new BullsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bulls, container, false);
        ButterKnife.bind(this, view);

        getPresenter().requestBulls();
        return view;
    }

    @NonNull
    @Override
    public BullsFragmentPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new BullsFragmentPresenter(context, applicationComponent);
    }

    @Override
    public void showBulls(List<Bull> bulls) {
        AnimalAdapter<Bull> myBullsAdapter = new AnimalAdapter(bulls, new AnimalAdapter.AnimalItemClickListener() {
            @Override
            public void onClick(int position) {
                getActivity().startActivityForResult(ActionActivity.newIntent(getActivity(), ViewBullActivity.class, getPresenter().getBullPublicId(position)), VIEW_BULL);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(myBullsAdapter);
    }

    @Override
    public void refreshAdapter() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}