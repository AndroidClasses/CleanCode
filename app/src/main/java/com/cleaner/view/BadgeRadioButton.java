package com.cleaner.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cleaner.main.R;

/**
 * Created by yangfeng on 16-7-7.
 */
public class BadgeRadioButton extends RelativeLayout {
    private RadioButton radioButton;
    private TextView badgeView;

    private int labelId;
    private int iconId;

    public void setOnClickListener(int labelId, int iconId) {
        this.labelId = labelId;
        this.iconId = iconId;
        checkAndDisplay();
    }

    public BadgeRadioButton(Context context) {
        this(context, null);
    }

    public BadgeRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BadgeRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        radioButton = (RadioButton) findViewById(R.id.radio);
        if (null == radioButton) {
            throw new IllegalStateException("RadioButton with id name 'radio' was not found.");
        }
        badgeView = (TextView) findViewById(R.id.badge);
        if (null == badgeView) {
            throw new IllegalStateException("TextView with id name 'badge' was not found.");
        }

        checkAndDisplay();
    }

    private void checkAndDisplay() {
        if (null != radioButton) {
            if (labelId > 0) {
                radioButton.setText(labelId);
            }
            if (iconId > 0) {
                radioButton.setBackgroundResource(iconId);
            }
        }
    }

    public void setSelectFlag(boolean select) {
        radioButton.setChecked(select);
    }

    @Override
    public boolean performClick() {
        super.performClick();
        radioButton.performClick();
        return true;
    }

}
