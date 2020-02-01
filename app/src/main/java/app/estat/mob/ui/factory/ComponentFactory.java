package app.estat.mob.ui.factory;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;

import java.util.List;

import app.estat.mob.comp.photo.FormPhotoView;
import app.estat.mob.comp.picker.FormDatePicker;
import app.estat.mob.comp.spinner.FormSpinner;
import app.estat.mob.comp.spinner.FormSpinnerAdapter;
import app.estat.mob.comp.text.FormEditText;
import app.estat.mob.comp.text.FormTextView;
import app.estat.mob.db.type.FormSpinnerItem;

public class ComponentFactory {

    private ComponentFactory() {
    }

    public static FormPhotoView getFormPhotoViewComponent(Context context, @DrawableRes int iconRes, @DrawableRes int imageRes) {
        FormPhotoView component = new FormPhotoView(context);
        component.setIcon(iconRes);
        component.setPhotoImage(imageRes);
        return component;
    }

    public static FormEditText getFormEditTextComponent(Context context, @DrawableRes int iconRes, @StringRes int hintRes) {
        FormEditText component = new FormEditText(context);
        component.setHint(hintRes);
        component.setIcon(iconRes);
        return component;
    }

    public static FormTextView getFormTextViewComponent(Context context, @DrawableRes int iconRes, @StringRes int hintRes, String text) {
        FormTextView component = new FormTextView(context);
        component.setHint(hintRes);
        component.setIcon(iconRes);
        component.setText(text);
        return component;
    }

    public static FormSpinner getFormSpinnerComponent(Context context, @DrawableRes int iconRes, String hintRes, List<FormSpinnerItem> data, boolean hasTitle) {
        FormSpinner component = new FormSpinner(context);
        component.setIcon(iconRes);

        FormSpinnerAdapter adapter = new FormSpinnerAdapter(context, data, hintRes, hasTitle);
        component.getSpinner().setAdapter(adapter);
        component.getSpinner().setSelection(adapter.getCount());

        return component;
    }

    public static FormDatePicker getFormDatePickerComponent(FragmentActivity activity, @DrawableRes int iconRes, @StringRes int hintRes) {
        FormDatePicker component = new FormDatePicker(activity);
        component.setHint(hintRes);
        component.setIcon(iconRes);
        component.setFragmentManager(activity.getSupportFragmentManager());
        return component;
    }
}