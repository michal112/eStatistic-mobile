package pl.com.app.comp.view.recycler;

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
    public interface ModuleClickListener {
        void onClick(ImageView imageView, int position);
    }

    private final Context mContext;

    private final List<Module> mModules;

    private final ModuleClickListener mModuleClickListener;

    public ModuleAdapter(Context mContext, List<Module> mModules, ModuleClickListener mModuleClickListener) {
        this.mContext = mContext;
        this.mModules = mModules;
        this.mModuleClickListener = mModuleClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ModuleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_dashboard_item, parent, false), mModuleClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ModuleViewHolder) {
            ModuleViewHolder moduleViewHolder = ((ModuleViewHolder) holder);
            Module module = mModules.get(position);
            moduleViewHolder.bindName(getResId(mContext, module.getNameRes()));
            moduleViewHolder.bindDescription(getResId(mContext, module.getDescriptionRes()));
            moduleViewHolder.bindImage(getResId(mContext, module.getIconRes()));
        }
    }

    private int getResId(Context context, String res) {
        return context.getResources().getIdentifier(res.substring(res.indexOf(".", 2) + 1),
                res.substring(res.indexOf(".") + 1, res.indexOf(".", 2)), context.getPackageName());
    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }

    public static class ModuleViewHolder extends RecyclerView.ViewHolder
            implements RecyclerView.OnClickListener {

        ImageView mImage;

        TextView mName;

        TextView mDescription;

        private ModuleClickListener mModuleClickListener;

        public ModuleViewHolder(View itemView, ModuleClickListener moduleClickListener) {
            super(itemView);

            mImage = itemView.findViewById(R.id.fragment_dashboard_item_image);
            mName = itemView.findViewById(R.id.fragment_dashboard_item_name);
            mDescription = itemView.findViewById(R.id.fragment_dashboard_item_description);
            itemView.setOnClickListener(this);

            this.mModuleClickListener = moduleClickListener;
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

        @Override
        public void onClick(View v) {
            mModuleClickListener.onClick(mImage, getAdapterPosition() - 1);
        }
    }
}
