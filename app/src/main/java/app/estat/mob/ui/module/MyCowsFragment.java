package app.estat.mob.ui.module;

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
import app.estat.mob.comp.recycler.AnimalAdapter;
import app.estat.mob.comp.recycler.AnimalRecyclerView;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Cow;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.module.MyCowsFragmentPresenter;
import app.estat.mob.mvp.view.module.MyCowsFragmentView;
import app.estat.mob.ui.action.ActionActivity;
import app.estat.mob.ui.action.ViewCowActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCowsFragment extends MvpBaseFragment<MyCowsFragmentPresenter, MyCowsFragmentView>
        implements MyCowsFragmentView {

    public static final int VIEW_COW = 1;

    @BindView(R.id.fragment_my_cows_recycler_view)
    AnimalRecyclerView mRecyclerView;

    public static MyCowsFragment newInstance() {
        return new MyCowsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cows, container, false);
        ButterKnife.bind(this, view);

        getPresenter().requestCows();
        return view;
    }

    @NonNull
    @Override
    public MyCowsFragmentPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new MyCowsFragmentPresenter(context, applicationComponent);
    }

    @Override
    public void showCows(List<Cow> cows) {
        AnimalAdapter<Cow> myCowsAdapter = new AnimalAdapter(cows, new AnimalAdapter.AnimalItemClickListener() {
            @Override
            public void onClick(int position) {
                getActivity().startActivityForResult(ActionActivity.newIntent(getActivity(), ViewCowActivity.class, getPresenter().getCowPublicId(position)), VIEW_COW);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(myCowsAdapter);
    }

    @Override
    public void refreshAdapter() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
