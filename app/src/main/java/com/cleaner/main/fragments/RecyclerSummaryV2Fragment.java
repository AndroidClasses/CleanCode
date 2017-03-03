package com.cleaner.main.fragments;

import android.os.Bundle;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class RecyclerSummaryV2Fragment extends BaseTabFragment {

    // region Constructors
    public RecyclerSummaryV2Fragment() {
    }
    // endregion

    // region Factory Methods
    public static RecyclerSummaryV2Fragment newInstance() {
        return new RecyclerSummaryV2Fragment();
    }

    public static RecyclerSummaryV2Fragment newInstance(Bundle extras) {
        RecyclerSummaryV2Fragment fragment = new RecyclerSummaryV2Fragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion
}
