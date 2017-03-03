package com.cleaner.main.fragments;

import android.os.Bundle;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class ContactListFragment extends BaseTabFragment {

    // region Constructors
    public ContactListFragment() {
    }
    // endregion

    // region Factory Methods
    public static ContactListFragment newInstance() {
        return new ContactListFragment();
    }

    public static ContactListFragment newInstance(Bundle extras) {
        ContactListFragment fragment = new ContactListFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion
}
