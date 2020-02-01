package app.estat.mob.comp.picker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import app.estat.mob.comp.R;


public class FormDatePicker extends ConstraintLayout {

    private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    private static final float ICON_ALPHA = 0.54f;

    TextView mTextView;

    ImageView mImageView;

    Date mDate;

    public FormDatePicker(Context context) {
        this(context, null);
    }

    public FormDatePicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormDatePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        String layoutInflaterService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(layoutInflaterService);
        View view = layoutInflater.inflate(R.layout.component_form_date_picker, this, true);
        mTextView = view.findViewById(R.id.component_form_date_picker_text);
        mImageView = view.findViewById(R.id.component_form_date_picker_icon);

        mImageView.setAlpha(ICON_ALPHA);
    }

    public void setHint(@StringRes int hint) {
        mTextView.setHint(hint);
    }

    public void setIcon(@DrawableRes int icon) {
        mImageView.setImageResource(icon);
    }

    public void setFragmentManager(final FragmentManager fragmentManager) {
        mTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance();
                datePickerFragment.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar date = Calendar.getInstance();
                        date.set(Calendar.YEAR, year);
                        date.set(Calendar.MONTH, month);
                        date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        Date time = date.getTime();
                        mDate = time;
                        mTextView.setText(DATE_FORMAT.format(time));
                    }
                });
                datePickerFragment.show(fragmentManager, DatePickerFragment.TAG);
            }
        });
    }

    public Date getDate() {
        return mDate;
    }
}
