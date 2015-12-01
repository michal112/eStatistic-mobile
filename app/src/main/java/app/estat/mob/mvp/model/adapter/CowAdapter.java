package app.estat.mob.mvp.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.estat.mob.R;
import app.estat.mob.mvp.model.item.CowItem;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CowAdapter extends RecyclerView.Adapter<CowAdapter.ViewHolder> {

    private final List<CowItem> mCows;

    public CowAdapter(List<CowItem> cows) {
        this.mCows = cows;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.main_fragment_recycler_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mCowDesc.setText(mCows.get(position).name);
        holder.mCowName.setText(mCows.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mCows.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_cow_name)
        TextView mCowName;

        @Bind(R.id.tv_cow_desc)
        TextView mCowDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

}
