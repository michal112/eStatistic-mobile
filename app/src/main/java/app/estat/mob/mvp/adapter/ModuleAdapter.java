package app.estat.mob.mvp.adapter;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.estat.mob.R;
import app.estat.mob.db.entity.Module;

import app.estat.mob.mvp.util.ViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {
    private Context mContext;

    private final List<Module> mModules;

    public ModuleAdapter(List<Module> modules, Context context) {
        this.mModules = modules;
        mContext = context;
    }

    @Override
    public ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fragment_dashboard_item, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ModuleViewHolder holder, int position) {
        holder.bindName(ViewUtils.getResId(mContext, mModules.get(position % 4).getNameRes()));
        holder.bindDescription(ViewUtils.getResId(mContext, mModules.get(position % 4).getDescriptionRes()));
        holder.bindImage(ViewUtils.getResId(mContext, mModules.get(position % 4).getIconRes()));
    }

    @Override
    public int getItemCount() {
        return mModules.size() * 4;
    }

    public static class ModuleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fragment_dashboard_item_image)
        ImageView mImage;

        @BindView(R.id.fragment_dashboard_item_name)
        TextView mName;

        @BindView(R.id.fragment_dashboard_item_description)
        TextView mDescription;

        public ModuleViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bindName(@StringRes int resId) {
            mName.setText(resId);
        }

        public void bindDescription(@StringRes int resId) {
            mDescription.setText(resId);
        }

        public void bindImage(@DrawableRes int resId) {
           mImage.setImageResource(resId);
        }
    }
}
