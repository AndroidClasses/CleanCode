package com.cleaner.home;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yangfeng on 16-6-28.
 */
public class ToastWrapper {
    private String labelText;
    private int labelId;
    private int lengthType;

    private Toast mShowToast;

    public ToastWrapper(int resId, int length) {
        labelId = resId;
        lengthType = length;
    }

    public ToastWrapper(String text, int length) {
        labelText = text;
        lengthType = length;
    }

    public void show(Context context) {
        if (cancel()) {
            // do nothing as the toast existing.
        } else {
            if (labelId > 0) {
                mShowToast = Toast.makeText(context, labelId, lengthType);
            } else {
                mShowToast = Toast.makeText(context, labelText, lengthType);
            }
        }

        mShowToast.show();
    }

    public boolean cancel() {
        if (null != mShowToast) {
            mShowToast.cancel();
            return true;
        }
        return false;
    }
}
