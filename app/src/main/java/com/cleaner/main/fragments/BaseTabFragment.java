package com.cleaner.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cleaner.main.R;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public abstract class BaseTabFragment extends Fragment {

    // region Constructors
    public BaseTabFragment() {
    }
    // endregion

    // region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_dummy_tab, container, false);
        TextView textView = (TextView)rootView.findViewById(R.id.dummy_textView);
        textView.setText(this.getClass().getSimpleName());
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    // endregion
}
