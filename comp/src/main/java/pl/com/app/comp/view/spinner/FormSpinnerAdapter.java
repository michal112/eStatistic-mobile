package pl.com.app.comp.view.spinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import app.estat.mob.db.type.FormSpinnerItem;
import pl.com.app.comp.R;

public class FormSpinnerAdapter extends ArrayAdapter<FormSpinnerItem>  {

    private Context mContext;

    private List<FormSpinnerItem> mData;

    private boolean mHasTitle;

    /*
    * @param hasTitle is true when item has title in string format, false when it's string reference
     */
    public FormSpinnerAdapter(@NonNull Context context, List<FormSpinnerItem> data, final String hint, boolean hasTitle) {
        super(context, R.layout.component_spinner_item, R.id.component_spinner_item_title, data);

        this.mHasTitle = hasTitle;
        this.mContext = context;
        this.mData = data;
        this.mData.add(new FormSpinnerItem() {
            @Override
            public String getTitle() {
                return hint;
            }
        });
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getFormSpinnerView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getFormSpinnerView(position, convertView, parent);
    }

    private View getFormSpinnerView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.component_spinner_item, null);
        }

        TextView titleView = convertView.findViewById(R.id.component_spinner_item_title);
        String title = mData.get(position).getTitle();
        if (position == getCount()) {
            titleView.setHint(getTitleResId(mContext, title));
        } else {
            if (!mHasTitle) {
                titleView.setText(getTitleResId(mContext, title));
            } else {
                titleView.setText(title);
            }
        }

        return convertView;
    }

    @Override
    public int getCount() {
        int count =  super.getCount();
        return count > 0 ? count - 1 : count;
    }

    private int getTitleResId(Context context, String res) {
        return context.getResources().getIdentifier(res.substring(res.indexOf(".", 2) + 1),
                "string", context.getPackageName());
    }
}
