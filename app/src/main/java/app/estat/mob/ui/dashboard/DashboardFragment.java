package app.estat.mob.ui.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Module;
import app.estat.mob.mvp.adapter.ModuleAdapter;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.dashboard.DashboardFragmentPresenter;
import app.estat.mob.mvp.util.ActivityUtil;
import app.estat.mob.mvp.view.dashboard.DashboardFragmentView;

import app.estat.mob.ui.module.AverageProductivityActivity;
import app.estat.mob.ui.module.FarmCardActivity;
import app.estat.mob.ui.module.MilkProductionActivity;
import app.estat.mob.ui.module.MyCowsActivity;
import butterknife.BindString;
import butterknife.BindView;

public class DashboardFragment extends MvpBaseFragment<DashboardFragmentPresenter, DashboardFragmentView>
        implements DashboardFragmentView {
    private static final String TAG = DashboardFragment.class.getName();

    @BindString(R.string.transition_module_image)
    String mTransitionName;

    @BindView(R.id.fragment_dashboard_recycler_view)
    RecyclerView mRecyclerView;

    private final ModuleAdapter.ModuleClickListener
            mModuleClickListener = new ModuleAdapter.ModuleClickListener() {
        @Override
        public void onClick(ImageView imageView, int position) {
            moduleClicked(imageView, getPresenter().getModule(position));
        }
    };

    private void moduleClicked(ImageView imageView, Module module) {
        if (module == null) {
            return;
        }

        switch (module.getActivity()) {
            case FARM_CARD:
                ActivityUtil.animateModuleActivity(getActivity(),
                    FarmCardActivity.newIntent(getActivity(), module.getIconRes(),
                        module.getNameRes()), imageView, mTransitionName);
                break;
            case AVERAGE_PRODUCTIVITY:
                ActivityUtil.animateModuleActivity(getActivity(),
                        AverageProductivityActivity.newIntent(getActivity(), module.getIconRes(),
                                module.getNameRes()), imageView, mTransitionName);
                break;
            case MY_COWS:
                ActivityUtil.animateModuleActivity(getActivity(),
                        MyCowsActivity.newIntent(getActivity(), module.getIconRes(),
                                module.getNameRes()), imageView, mTransitionName);
                break;
            case MILK_PRODUCTION:
                ActivityUtil.animateModuleActivity(getActivity(),
                        MilkProductionActivity.newIntent(getActivity(), module.getIconRes(),
                                module.getNameRes()), imageView, mTransitionName);
                break;
            default:
                Log.d(TAG, "Unknown module clicked");
                break;
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

        getPresenter().requestModules();
    }

    @Override
    public void showModules(List<Module> modules) {
        ModuleAdapter moduleAdapter = new ModuleAdapter(getActivity(), modules, mModuleClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(moduleAdapter);
    }
}
