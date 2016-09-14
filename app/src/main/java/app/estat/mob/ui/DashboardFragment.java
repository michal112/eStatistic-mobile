package app.estat.mob.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Module;
import app.estat.mob.mvp.adapter.ModuleAdapter;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.DashboardFragmentPresenter;
import app.estat.mob.mvp.view.DashboardFragmentView;

import butterknife.BindView;

public class DashboardFragment extends MvpBaseFragment<DashboardFragmentPresenter, DashboardFragmentView>
        implements DashboardFragmentView {
    private static final String TAG = DashboardFragment.class.getName();

    @BindView(R.id.fragment_dashboard_recycler_view)
    RecyclerView mRecyclerView;

    private final ModuleAdapter.ModuleClickListener
            mModuleClickListener = new ModuleAdapter.ModuleClickListener() {
        @Override
        public void onClick(int position) {
            moduleClicked(getPresenter().getModule(position));
        }
    };

    private void moduleClicked(Module module) {
        if (module == null) {
            return;
        }

        switch (module.getActivity()) {
            case FARM_CARD:
                FarmCardActivity.newIntent(getActivity());
                break;
            default:
                Log.d(TAG, "Unknown module clicked");
        }
    }

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_dashboard;
    }

    @NonNull
    @Override
    public DashboardFragmentPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new DashboardFragmentPresenter(applicationComponent);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ModuleAdapter moduleAdapter = new ModuleAdapter(getActivity(),
                getPresenter().requestModules(), mModuleClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(moduleAdapter);
    }
}
