package com.cleaner.main.fragments;

import android.os.Bundle;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class ExploreFragment extends BaseTabFragment {

    // region Constructors
    public ExploreFragment() {
    }
    // endregion

    // region Factory Methods
    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    public static ExploreFragment newInstance(Bundle extras) {
        ExploreFragment fragment = new ExploreFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion
}
