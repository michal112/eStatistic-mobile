package app.estat.mob.comp.picker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    public static final String TAG = DatePickerFragment.class.getName();

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    protected static DatePickerFragment newInstance() {
        return new DatePickerFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), onDateSetListener,  year, month, day);
    }

    public void setOnDateSetListener(DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.onDateSetListener = onDateSetListener;
    }
}
