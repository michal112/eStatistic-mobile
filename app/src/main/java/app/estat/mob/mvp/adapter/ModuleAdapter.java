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

public class ModuleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int HEADER = 0;

    private final static int MODULE = 1;

    private final Context mContext;

    private final List<Module> mModules;

    public ModuleAdapter(List<Module> modules, Context context) {
        this.mModules = modules;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch(viewType) {
            case HEADER:
                return new HeaderHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recycler_view_header, parent, false));
            case MODULE:
                return new ModuleViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.fragment_dashboard_item, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ModuleViewHolder) {
            ModuleViewHolder moduleViewHolder = ((ModuleViewHolder) holder);
            moduleViewHolder.bindName(ViewUtils.getResId(mContext, mModules.get(position % 4).getNameRes()));
            moduleViewHolder.bindDescription(ViewUtils.getResId(mContext, mModules.get(position % 4).getDescriptionRes()));
            moduleViewHolder.bindImage(ViewUtils.getResId(mContext, mModules.get(position % 4).getIconRes()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position > 0 ? MODULE : HEADER;
    }

    @Override
    public int getItemCount() {
        return mModules.size() * 4 + 1;
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

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }
}
