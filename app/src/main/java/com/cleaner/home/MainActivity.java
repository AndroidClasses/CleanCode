package com.cleaner.home;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.cleaner.config.MainPageConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends TabActivity implements OnCheckedChangeListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(android.R.id.tabhost)
    TabHost mTabHost;
    @BindView(R.id.radio_button_panel1)
    FrameLayout radio_button_panel1;
    @BindView(R.id.radio_button1)
    RadioButton mRadioButton_1;
    @BindView(R.id.radio_button_panel2)
    RelativeLayout radio_button_panel2;
    @BindView(R.id.radio_button2)
    RadioButton mRadioButton_2;
    @BindView(R.id.radio_button_panel3)
    FrameLayout radio_button_panel3;
    @BindView(R.id.radio_button3)
    RadioButton mRadioButton_3;
    @BindView(R.id.radio_button_panel4)
    FrameLayout radio_button_panel4;
    @BindView(R.id.radio_button4)
    RadioButton mRadioButton_4;
    @BindView(R.id.radio_button_panel5)
    FrameLayout radio_button_panel5;
    @BindView(R.id.radio_button5)
    RadioButton mRadioButton_5;

    public static final int RADIO_MAIN = 0;
    public static final int RADIO_CONVERSATION = 1;
    public static final int RADIO_PATIENT = 2;
    public static final int RADIO_DISCOVERY = 3;
    public static final int RADIO_MY = 4;

    public static final String WHICH_ACTIVITY = "which_activity";
    static int whichActivity = RADIO_MAIN;

    public static final String TAB_ITEM_MAIN = "tabitem_amin";
    public static final String TAB_ITEM_CONVERSATION = "tabitem_conversation";
    public static final String TAB_ITEM_PATIENT = "tabitem_patient";
    public static final String TAB_ITEM_MY = "tabitem_my";
    public static final String TAB_ITEM_DISCOVERY = "tabitem_discovery";
    private TabSpec tabConversation;

    private MainPageConfig mainPageConfig;

    private RadioButton mCurrentButton;
    private long mExitTime;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            setWhichActivity(savedInstanceState.getInt(WHICH_ACTIVITY, RADIO_MAIN));
        }

        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        unbinder = ButterKnife.bind(this);

        bindUnreadCountTextView(R.id.tv_tipMsgcnt);

//        checkAndLoginXbkpIM();
//        PatientApplication.getInstance().addActivity(this);

        mainPageConfig = new MainPageConfig(this);

        initTabs();

        //事件的监听
//        AndroidEventManager.getInstance().addEventListener(
//                EventCode.LoginActivityLaunched, this, false);
//        AndroidEventManager.getInstance().addEventListener(
//                EventCode.UnreadMessageCountChanged, this, false);
//
//        //显示未读消息数
//        setRecentChatUnreadNumber(RecentChatManager.getInstance()
//                .getUnreadMessageTotalCount());
    }

    private TabSpec getHomePage() {
        TabSpec tabNearby = null;
        if (mainPageConfig.hasSummary()) {
            tabNearby = mTabHost.newTabSpec(TAB_ITEM_MAIN);
            tabNearby.setIndicator(TAB_ITEM_MAIN).setContent(new Intent(this, SummaryActivity.class));
        } else if (mainPageConfig.hasRecyclerSummary()) {
            tabNearby = mTabHost.newTabSpec(TAB_ITEM_MAIN);
            tabNearby.setIndicator(TAB_ITEM_MAIN).setContent(new Intent(this, RecyclerSummaryActivity.class));
        } else if (mainPageConfig.hasRecyclerSummaryV2()) {
            tabNearby = mTabHost.newTabSpec(TAB_ITEM_MAIN);
            tabNearby.setIndicator(TAB_ITEM_MAIN).setContent(new Intent(this, RecyclerSummaryV2Activity.class));
        } else {
            // Temporarily not processed
        }

        return tabNearby;
    }

    private TabSpec getMessagePage() {
        TabSpec tabMessage = null;
        if (mainPageConfig.hasMessage()) {
            tabConversation = mTabHost.newTabSpec(TAB_ITEM_CONVERSATION);
            initOrUpdateConversationTab();
            tabMessage = tabConversation;
        } else {
            Log.e(TAG, "message is null");
        }
        return tabMessage;
    }

    private TabSpec getDoctorPage() {
        TabSpec tabFind = null;
        if (mainPageConfig.hasDoctorPage()) {
            tabFind = mTabHost.newTabSpec(TAB_ITEM_PATIENT);
            tabFind.setIndicator(TAB_ITEM_PATIENT).setContent(
                    new Intent(this, TabSearchDoctorActivity.class));
        } else {
            // Temporarily not processed
        }

        return tabFind;
    }

    private TabSpec getMinePage() {
        TabSpec tabMy = null;
        if (mainPageConfig.hasMinePage()) {
            tabMy = mTabHost.newTabSpec(TAB_ITEM_MY);
            tabMy.setIndicator(TAB_ITEM_MY).setContent(
                    new Intent(this, MineActivity.class));
        } else {
            // Temporarily not processed
        }
        return tabMy;
    }

    private TabSpec getDiscoveryPage() {
        TabSpec tabDiscovery = null;
        if (mainPageConfig.hasDiscoveryPage()) {
            tabDiscovery = mTabHost.newTabSpec(TAB_ITEM_DISCOVERY);
            tabDiscovery.setIndicator(TAB_ITEM_DISCOVERY).setContent(
                    new Intent(this, DiscoveryActivity.class));
        } else {
            // Temporarily not processed
        }
        return tabDiscovery;
    }

    private boolean initTabPanel(TabSpec tabSpec, View panel, View tabView, RadioClickListener listener) {
        if (null == tabSpec) {
            panel.setVisibility(View.GONE);
            return false;
        } else {
            mTabHost.addTab(tabSpec);
            tabView.setOnClickListener(listener);
            panel.setVisibility(View.VISIBLE);
        }
        return true;
    }

    private void initTabs() {
        initSelectTab(getIntent());
        initRadioButtons();
        RadioClickListener listener = new RadioClickListener();
        // Home page
        TabSpec tabNearby = getHomePage();
        initTabPanel(tabNearby, radio_button_panel1, mRadioButton_1, listener);

        //message
        TabSpec tabMessage = getMessagePage();
        initTabPanel(tabMessage, radio_button_panel2, mRadioButton_2, listener);

        //find doctor
        TabSpec tabDoctor = getDoctorPage();
        initTabPanel(tabDoctor, radio_button_panel3, mRadioButton_3, listener);

        //discovery
        TabSpec tabDiscovery = getDiscoveryPage();
        initTabPanel(tabDiscovery, radio_button_panel5, mRadioButton_5, listener);

        //me
        TabSpec tabMy = getMinePage();
        initTabPanel(tabMy, radio_button_panel4, mRadioButton_4, listener);
    }

    private static void setWhichActivity(int which) {
        whichActivity = which;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(WHICH_ACTIVITY, whichActivity);
    }

    private void initRadioButtons() {
        switch (whichActivity) {
            case RADIO_MAIN:
                if (mRadioButton_1 != null) {
                    radioClick(R.id.radio_button1);
                }
                break;
            case RADIO_CONVERSATION:
                if (mRadioButton_2 != null) {
                    radioClick(R.id.radio_button2);
                }
                break;
            case RADIO_PATIENT:
                if (mRadioButton_3 != null) {
                    radioClick(R.id.radio_button3);
                }
                break;
            case RADIO_DISCOVERY:
                if (mRadioButton_5 != null) {
                    radioClick(R.id.radio_button5);
                }
                break;
            case RADIO_MY:
                if (mRadioButton_4 != null) {
                    radioClick(R.id.radio_button4);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        parseNewIntent(intent);
        initSelectTab(intent);
        initRadioButtons();
    }

    private void initSelectTab(Intent intent) {
        setWhichActivity(RADIO_MAIN);
        setWhichActivity(intent.getIntExtra(WHICH_ACTIVITY, RADIO_MAIN));

    }

    @Override
    protected void onResume() {
        super.onResume();

        //如果是“我的医生”页，右上角“找医生”跳转过来的，手动触发“找医生界面”
        Intent intent = getIntent();

        String docter = intent.getStringExtra("callDocter");

        if ("true".equals(docter)) {
            intent.removeExtra("callDocter");
            mRadioButton_3.performClick();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        radioClick(checkedId);
    }

//    @Override
//    public void eventRunEnd(Event arg0) {
//        if (arg0.getEventCode() == EventCode.LoginActivityLaunched) {
//            finish();
//        } else if (arg0.getEventCode() == EventCode.UnreadMessageCountChanged) {
//            setRecentChatUnreadNumber(RecentChatManager.getInstance()
//                    .getUnreadMessageTotalCount());
//        }
//    }

    private class RadioClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            radioClick(v.getId());
        }
    }

    private void radioClick(int viewid) {
        switch (viewid) {
            case R.id.radio_button1:
                mTabHost.setCurrentTabByTag(TAB_ITEM_MAIN);
                updateRadioButtons(RADIO_MAIN);
                reportTabClickEvent(0);
                break;
            case R.id.radio_button2:
                mTabHost.setCurrentTabByTag(TAB_ITEM_CONVERSATION);
                updateRadioButtons(RADIO_CONVERSATION);
                reportTabClickEvent(1);
                break;
            case R.id.radio_button3:
                mTabHost.setCurrentTabByTag(TAB_ITEM_PATIENT);
                updateRadioButtons(RADIO_PATIENT);
                reportTabClickEvent(4);
                break;
            case R.id.radio_button4:
                mTabHost.setCurrentTabByTag(TAB_ITEM_MY);
                updateRadioButtons(RADIO_MY);
                reportTabClickEvent(3);
                break;
            case R.id.radio_button5:
                mTabHost.setCurrentTabByTag(TAB_ITEM_DISCOVERY);
                updateRadioButtons(RADIO_DISCOVERY);
                reportTabClickEvent(5);
                break;
            default:
                break;
        }
    }

    private void updateRadioButtons(int i) {
        setWhichActivity(i);
        if (null != mCurrentButton) {
            mCurrentButton.setChecked(false);
        }
        switch (i) {
            case RADIO_MAIN:
                mCurrentButton = mRadioButton_1;
                break;
            case RADIO_CONVERSATION:
                mCurrentButton = mRadioButton_2;
                break;
            case RADIO_PATIENT:
                mCurrentButton = mRadioButton_3;
                break;
            case RADIO_MY:
                mCurrentButton = mRadioButton_4;
                break;
            case RADIO_DISCOVERY:
                mCurrentButton = mRadioButton_5;
                break;
            default:
                break;
        }

        if (null != mCurrentButton) {
            mCurrentButton.setChecked(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTabHost = null;
        mRadioButton_1 = null;
        mRadioButton_2 = null;
        mRadioButton_3 = null;
        mRadioButton_4 = null;
        mRadioButton_5 = null;

//        AndroidEventManager.getInstance().removeEventListener(
//                EventCode.LoginActivityLaunched, this);
//        AndroidEventManager.getInstance().removeEventListener(
//                EventCode.UnreadMessageCountChanged, this);
        unbinder.unbind();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 3000) {
                Toast.makeText(this, R.string.toast_exit, Toast.LENGTH_LONG).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    public void gotoSearchView() {
//        updateRadioButtons(RADIO_PATIENT);
        radioClick(R.id.radio_button3);
    }

//    @Override
//    protected String getRoleType() {
//        return IntentUtils.getRoleType();
//    }
//
//    @Override
//    protected void gotoLoginActivity() {
//        IntentUtils.gotoLoginActivity(this, true);
//    }

//    private void checkAndLoginXbkpIM() {
//        if (IMApplication.getInstance().isMyServerLogined()) {
//            DemoApplication.loginIM();
//        }
//    }

    /**
     * 初始化或者更新消息tab页
     */
    private void initOrUpdateConversationTab() {
        if (tabConversation != null) {
            tabConversation.setIndicator(TAB_ITEM_CONVERSATION).setContent(
                    new Intent(this, Message_TabActivity.class));
        }
    }

//    @Override
//    protected void onLoginCompleted(String result) {
//        super.onLoginCompleted(result);
//        checkAndLoginXbkpIM();
//
//        //同步设置免打扰的讨论组
//        requestGroupChatSetting();
//
//    }
//
//    @Override
//    protected void switchConversationTab(int originalvalue) {
//        super.switchConversationTab(originalvalue);
//        int currentValue = BaseConstantDef.USED_XB_VALUE;
//        if (originalvalue != 0) {
//            if (originalvalue == currentValue) {
//                Logger.i("used same im , no need switch tab");
//            } else {
//                initOrUpdateConversationTab();
//            }
//        }
//    }

    // dumb override methods
    protected void parseNewIntent(Intent intent) {

    }
    protected void reportTabClickEvent(int tabIndex) {

    }
    protected void bindUnreadCountTextView(int viewId) {

    }
}
