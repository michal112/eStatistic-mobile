package app.estat.mob.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import app.estat.mob.ui.module.MyBullsActivity;
import app.estat.mob.ui.module.MyCowsActivity;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardFragment extends MvpBaseFragment<DashboardFragmentPresenter, DashboardFragmentView>
        implements DashboardFragmentView {
    private static final String TAG = DashboardFragment.class.getName();

    public static final int FARM_CARD_EDIT = 0;

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
                ActivityUtil.animateModuleActivityForResult(getActivity(),
                    FarmCardActivity.newIntent(getActivity(), module.getIconRes(),
                        module.getNameRes()), imageView, mTransitionName, FARM_CARD_EDIT);
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
            case MY_BULLS:
                ActivityUtil.animateModuleActivity(getActivity(),
                        MyBullsActivity.newIntent(getActivity(), module.getIconRes(),
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);

        getPresenter().requestModules();
        return view;
    }

    @NonNull
    @Override
    public DashboardFragmentPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new DashboardFragmentPresenter(context, applicationComponent);
    }

    @Override
    public void showModules(List<Module> modules) {
        ModuleAdapter moduleAdapter = new ModuleAdapter(getActivity(), modules, mModuleClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(moduleAdapter);
    }
}
