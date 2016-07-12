package com.cleaner.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yangfeng on 16-7-5.
 */
abstract public class BaseTabActivity extends FragmentActivity {
    @BindView(R.id.dummy_textView)
    TextView mDummyText;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dummy_tab);
        unbinder = ButterKnife.bind(this);

        mDummyText.setText(this.getLocalClassName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
