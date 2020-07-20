package app.estat.mob.comp.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.estat.mob.comp.R;

public class FormCardView extends CardView {

    TextView mName;

    LinearLayout mContainer;

    public FormCardView(@NonNull Context context) {
        this(context, null);
    }

    public FormCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        String inflaterService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(inflaterService);
        View view = inflater.inflate(R.layout.component_form_card_view, this, true);
        mName = view.findViewById(R.id.component_form_card_view_name);
        mContainer = view.findViewById(R.id.component_form_card_view_container);

        setName(context, attrs);
    }

    private void setName(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormCardView);
        String string = typedArray.getString(R.styleable.FormCardView_formCardViewTitle);
        mName.setText(string);
        typedArray.recycle();
    }

    public void insertView(ViewGroup view) {
        mContainer.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
