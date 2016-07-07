package com.cleaner.home;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.cleaner.config.MainPageConfig;
import com.cleaner.view.BadgeRadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends TabActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(android.R.id.tabhost)
    TabHost tabHost;

    @BindView(R.id.summaryBadgeRadio)
    BadgeRadioButton summaryBadgeRadio;

    @BindView(R.id.conversationBadgeRadio)
    BadgeRadioButton conversationBadgeRadio;

    @BindView(R.id.contactBadgeRadio)
    BadgeRadioButton contactBadgeRadio;

    @BindView(R.id.exploreBadgeRadio)
    BadgeRadioButton exploreBadgeRadio;

    @BindView(R.id.profileBadgeRadio)
    BadgeRadioButton profileBadgeRadio;

    private static final int INDEX_SUMMARY = 0;
    private static final int INDEX_CONVERSATION = 1;
    private static final int INDEX_CONTACT = 2;
    private static final int INDEX_EXPLORE = 3;
    private static final int INDEX_PROFILE = 4;
    private static int activeIndex = INDEX_SUMMARY;

    private static final String ACTIVE_INDEX_KEY = "ACTIVE_INDEX_KEY";

    private static final String TAB_SPEC_SUMMARY = "TAB_SPEC_SUMMARY";
    private static final String TAB_SPEC_CONVERSATION = "TAB_SPEC_CONVERSATION";
    private static final String TAB_SPEC_CONTACT = "TAB_SPEC_CONTACT";
    private static final String TAB_SPEC_EXPLORE = "TAB_SPEC_EXPLORE";
    private static final String TAB_SPEC_PROFILE = "TAB_SPEC_PROFILE";
    private TabSpec tabConversation;

    private static final int EVENT_SUMMARY = 0;
    private static final int EVENT_CONVERSATION = 1;
    private static final int EVENT_CONTACT = 4;
    private static final int EVENT_EXPLORE = 5;
    private static final int EVENT_PROFILE = 3;

    private MainPageConfig mainPageConfig;

    private BadgeRadioButton activeBadgeRadio;

    private long exitInterval;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            setActiveIndex(savedInstanceState.getInt(ACTIVE_INDEX_KEY, INDEX_SUMMARY));
        }

        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        unbinder = ButterKnife.bind(this);

        bindUnreadCountTextView(conversationBadgeRadio);

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
            tabNearby = tabHost.newTabSpec(TAB_SPEC_SUMMARY);
            tabNearby.setIndicator(TAB_SPEC_SUMMARY).setContent(new Intent(this, SummaryActivity.class));
        } else if (mainPageConfig.hasRecyclerSummary()) {
            tabNearby = tabHost.newTabSpec(TAB_SPEC_SUMMARY);
            tabNearby.setIndicator(TAB_SPEC_SUMMARY).setContent(new Intent(this, RecyclerSummaryActivity.class));
        } else if (mainPageConfig.hasRecyclerSummaryV2()) {
            tabNearby = tabHost.newTabSpec(TAB_SPEC_SUMMARY);
            tabNearby.setIndicator(TAB_SPEC_SUMMARY).setContent(new Intent(this, RecyclerSummaryV2Activity.class));
        } else {
            // Temporarily not processed
        }

        return tabNearby;
    }

    private TabSpec getMessagePage() {
        TabSpec tabMessage = null;
        if (mainPageConfig.hasMessage()) {
            tabConversation = tabHost.newTabSpec(TAB_SPEC_CONVERSATION);
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
            tabFind = tabHost.newTabSpec(TAB_SPEC_CONTACT);
            tabFind.setIndicator(TAB_SPEC_CONTACT).setContent(
                    new Intent(this, TabSearchDoctorActivity.class));
        } else {
            // Temporarily not processed
        }

        return tabFind;
    }

    private TabSpec getMinePage() {
        TabSpec tabMy = null;
        if (mainPageConfig.hasMinePage()) {
            tabMy = tabHost.newTabSpec(TAB_SPEC_PROFILE);
            tabMy.setIndicator(TAB_SPEC_PROFILE).setContent(
                    new Intent(this, MineActivity.class));
        } else {
            // Temporarily not processed
        }
        return tabMy;
    }

    private TabSpec getDiscoveryPage() {
        TabSpec tabDiscovery = null;
        if (mainPageConfig.hasDiscoveryPage()) {
            tabDiscovery = tabHost.newTabSpec(TAB_SPEC_EXPLORE);
            tabDiscovery.setIndicator(TAB_SPEC_EXPLORE).setContent(
                    new Intent(this, DiscoveryActivity.class));
        } else {
            // Temporarily not processed
        }
        return tabDiscovery;
    }
    private boolean initTabPanel(TabSpec tabSpec, BadgeRadioButton panel, int labelId, int iconId,
                                 RadioClickListener listener) {
        if (null == tabSpec) {
            panel.setVisibility(View.GONE);
            return false;
        } else {
            tabHost.addTab(tabSpec);
            panel.setOnClickListener(labelId, iconId, listener);
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
        initTabPanel(tabNearby, summaryBadgeRadio, R.string.tab_main, R.drawable.main_tab_home, listener);

        //message
        TabSpec tabMessage = getMessagePage();
        initTabPanel(tabMessage, conversationBadgeRadio, R.string.tab_message, R.drawable.main_tab_message, listener);

        //find doctor
        TabSpec tabDoctor = getDoctorPage();
        initTabPanel(tabDoctor, contactBadgeRadio, R.string.tab_find_doctor, R.drawable.main_tab_search, listener);

        //discovery
        TabSpec tabDiscovery = getDiscoveryPage();
        initTabPanel(tabDiscovery, exploreBadgeRadio, R.string.tab_discovery, R.drawable.main_tab_search, listener);

        //me
        TabSpec tabMy = getMinePage();
        initTabPanel(tabMy, profileBadgeRadio, R.string.tab_mine, R.drawable.main_tab_mine, listener);
    }

    private static void setActiveIndex(int which) {
        activeIndex = which;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ACTIVE_INDEX_KEY, activeIndex);
    }

    private void initRadioButtons() {
        switch (activeIndex) {
            case INDEX_SUMMARY:
                radioClick(summaryBadgeRadio);
                break;
            case INDEX_CONVERSATION:
                radioClick(conversationBadgeRadio);
                break;
            case INDEX_CONTACT:
                radioClick(contactBadgeRadio);
                break;
            case INDEX_EXPLORE:
                radioClick(exploreBadgeRadio);
                break;
            case INDEX_PROFILE:
                radioClick(profileBadgeRadio);
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
        setActiveIndex(INDEX_SUMMARY);
        setActiveIndex(intent.getIntExtra(ACTIVE_INDEX_KEY, INDEX_SUMMARY));

    }

    @Override
    protected void onResume() {
        super.onResume();

        //如果是“我的医生”页，右上角“找医生”跳转过来的，手动触发“找医生界面”
        Intent intent = getIntent();

        String docter = intent.getStringExtra("callDocter");

        if ("true".equals(docter)) {
            intent.removeExtra("callDocter");
            contactBadgeRadio.performClick();
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tabHost = null;

//        AndroidEventManager.getInstance().removeEventListener(
//                EventCode.LoginActivityLaunched, this);
//        AndroidEventManager.getInstance().removeEventListener(
//                EventCode.UnreadMessageCountChanged, this);
        unbinder.unbind();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitInterval) > 3000) {
                Toast.makeText(this, R.string.toast_exit, Toast.LENGTH_LONG).show();
                exitInterval = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    public void gotoSearchView() {
        radioClick(contactBadgeRadio);
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
            tabConversation.setIndicator(TAB_SPEC_CONVERSATION).setContent(
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
    protected void bindUnreadCountTextView(BadgeRadioButton view) {
        int viewId = view.findViewById(R.id.badge).getId();
    }

    // refactor tab view item.
    private void radioClick(BadgeRadioButton view) {
        radioClick(view.getId());
    }

    private void radioClick(int viewId) {
        setSelectFlag(false);

        switch (viewId) {
            case R.id.summaryBadgeRadio:
                tabHost.setCurrentTabByTag(TAB_SPEC_SUMMARY);
                setActiveIndex(INDEX_SUMMARY);
                reportTabClickEvent(EVENT_SUMMARY);
                break;
            case R.id.conversationBadgeRadio:
                tabHost.setCurrentTabByTag(TAB_SPEC_CONVERSATION);
                setActiveIndex(INDEX_CONVERSATION);
                reportTabClickEvent(EVENT_CONVERSATION);
                break;
            case R.id.contactBadgeRadio:
                tabHost.setCurrentTabByTag(TAB_SPEC_CONTACT);
                setActiveIndex(INDEX_CONTACT);
                reportTabClickEvent(EVENT_CONTACT);
                break;
            case R.id.exploreBadgeRadio:
                tabHost.setCurrentTabByTag(TAB_SPEC_EXPLORE);
                setActiveIndex(INDEX_EXPLORE);
                reportTabClickEvent(EVENT_EXPLORE);
                break;
            case R.id.profileBadgeRadio:
                tabHost.setCurrentTabByTag(TAB_SPEC_PROFILE);
                setActiveIndex(INDEX_PROFILE);
                reportTabClickEvent(EVENT_PROFILE);
                break;
            default:
                break;
        }

        activeBadgeRadio = ButterKnife.findById(tabHost, viewId);
        setSelectFlag(true);
    }

    private void setSelectFlag(boolean flag) {
        if (null != activeBadgeRadio) {
            activeBadgeRadio.setSelectFlag(flag);
        }
    }
}
