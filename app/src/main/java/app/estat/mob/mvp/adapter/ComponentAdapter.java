package app.estat.mob.mvp.adapter;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.estat.mob.R;
import app.estat.mob.db.entity.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ComponentAdapter<T extends Item> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> mData;

    private Context mContext;

    public ComponentAdapter(Context context, List<T> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ComponentViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_my_cows_recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ComponentViewHolder) {
            T mItem = mData.get(i);
            //((ComponentViewHolder) viewHolder).setIcon(mItem.getIcon());
            ((ComponentViewHolder) viewHolder).setName(mItem.getName());
            ((ComponentViewHolder) viewHolder).setNumber(mItem.getNumber());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ComponentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.component_module_recycler_view_name)
        TextView mName;

        @BindView(R.id.component_module_recycler_view_number)
        TextView mNumber;

        @BindView(R.id.component_module_recycler_view_icon)
        ImageView mIcon;

        public ComponentViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void setName(String name) {
            mName.setText(name);
        }

        public void setNumber(String number) {
            mNumber.setText(number);
        }

        public void setIcon(@DrawableRes int icon) {
            mIcon.setImageResource(icon);
        }
    }
}
