package com.cleaner.main.fragments;

import android.os.Bundle;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class RecyclerSummaryFragment extends BaseTabFragment {

    // region Constructors
    public RecyclerSummaryFragment() {
    }
    // endregion

    // region Factory Methods
    public static RecyclerSummaryFragment newInstance() {
        return new RecyclerSummaryFragment();
    }

    public static RecyclerSummaryFragment newInstance(Bundle extras) {
        RecyclerSummaryFragment fragment = new RecyclerSummaryFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion
}
