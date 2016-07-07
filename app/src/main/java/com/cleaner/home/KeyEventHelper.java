package com.cleaner.home;

import android.view.KeyEvent;

/**
 * Created by yangfeng on 16-6-28.
 */
public class KeyEventHelper {
    private KeyEventHelper() {
        // EMPTY
    }

    public static boolean isBackKeyDown(KeyEvent event) {
        return event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK;
    }
}
