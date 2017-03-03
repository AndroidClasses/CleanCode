package com.cleaner.main.fragments;

import android.os.Bundle;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class SummaryFragment extends BaseTabFragment {

    // region Constructors
    public SummaryFragment() {
    }
    // endregion

    // region Factory Methods
    public static SummaryFragment newInstance() {
        return new SummaryFragment();
    }

    public static SummaryFragment newInstance(Bundle extras) {
        SummaryFragment fragment = new SummaryFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion
}
