package com.cleaner.main.fragments;

import android.os.Bundle;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class ProfileFragment extends BaseTabFragment {

    // region Constructors
    public ProfileFragment() {
    }
    // endregion

    // region Factory Methods
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    public static ProfileFragment newInstance(Bundle extras) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion
}
