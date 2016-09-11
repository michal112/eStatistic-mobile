package app.estat.mob.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import app.estat.mob.R;
import app.estat.mob.mvp.adapter.ModuleAdapter;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.DashboardFragmentPresenter;
import app.estat.mob.mvp.view.DashboardFragmentView;
import butterknife.BindView;

public class DashboardFragment extends MvpBaseFragment<DashboardFragmentPresenter, DashboardFragmentView>
        implements DashboardFragmentView {

    @BindView(R.id.fragment_dashboard_recycler_view)
    RecyclerView mRecyclerView;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_dashboard;
    }

    @NonNull
    @Override
    public DashboardFragmentPresenter createPresenter() {
        return new DashboardFragmentPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ModuleAdapter moduleAdapter = new ModuleAdapter(getPresenter().requestModules(), getActivity());
    }
}
