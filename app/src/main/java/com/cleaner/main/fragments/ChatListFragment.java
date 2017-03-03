package com.cleaner.main.fragments;

import android.os.Bundle;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class ChatListFragment extends BaseTabFragment {

    // region Constructors
    public ChatListFragment() {
    }
    // endregion

    // region Factory Methods
    public static ChatListFragment newInstance() {
        return new ChatListFragment();
    }

    public static ChatListFragment newInstance(Bundle extras) {
        ChatListFragment fragment = new ChatListFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion
}
