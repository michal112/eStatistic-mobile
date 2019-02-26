package pl.com.app.comp.view.spinner;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import app.estat.mob.db.type.FormSpinnerItem;
import pl.com.app.comp.R;

public class FormSpinner extends ConstraintLayout {

    private static final float ICON_ALPHA = 0.54f;

    Spinner mSpinner;

    FormSpinnerItem mItem;

    ImageView mImageView;

    public FormSpinner(Context context) {
        this(context, null);
    }

    public FormSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        String layoutInflaterService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(layoutInflaterService);
        View view = inflater.inflate(R.layout.component_form_spinner, this, true);
        mImageView = view.findViewById(R.id.component_form_spinner_icon);
        mSpinner = view.findViewById(R.id.component_form_spinner);

        mImageView.setAlpha(ICON_ALPHA);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mSpinner.getItemAtPosition(position) instanceof FormSpinnerItem) {
                    mItem = (FormSpinnerItem) mSpinner.getItemAtPosition(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mItem = null;
            }
        });
    }

    public void setIcon(@DrawableRes int icon) {
        mImageView.setImageResource(icon);
    }

    public Spinner getSpinner() {
        return mSpinner;
    }

    public FormSpinnerItem getSelectedItem() {
        return mItem;
    }
}
