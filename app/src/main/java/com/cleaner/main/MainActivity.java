package com.cleaner.main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.cleaner.CleanCodeApplication;
import com.cleaner.view.BadgeRadioButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends TabActivity implements MainConfigContracts.ConfigView{
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

    private final ExitingTrigger exitingTrigger = new ExitingTrigger();
    private final ToastWrapper toastWrapper = new ToastWrapper(R.string.toast_exit, Toast.LENGTH_LONG);

    private Unbinder unbinder;
    private BadgeRadioButton activeBadgeRadio;

    private final RadioClickListener listener = new RadioClickListener();


    @Inject MainConfigPresenterImpl configPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            setActiveIndex(savedInstanceState.getInt(ACTIVE_INDEX_KEY, INDEX_SUMMARY));
        }

        // Create the presenter
        DaggerMainConfigComponent.builder()
                .configurationRepositoryComponent(CleanCodeApplication.get(this).getConfigurationRepositoryComponent())
                .mainConfigPresenterModule(new MainConfigPresenterModule(this)).build()
                .inject(this);

        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        unbinder = ButterKnife.bind(this);

        bindUnreadCountTextView(conversationBadgeRadio);

//        checkAndLoginXbkpIM();
//        PatientApplication.getInstance().addActivity(this);

//        MainConfigContracts.ConfigPresenter configPresenter = new MainConfigPresenterImpl(this, pageConfig);

        initSelectTab(getIntent());
        initRadioButtons();
        configPresenter.initTabs();

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

        String doctor = intent.getStringExtra("callDocter");

        if ("true".equals(doctor)) {
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

//    private PageConfig parsePageConfig() {
//        String pageConfigKey = MainPageConfigImpl.getPageConfigKey();
//        String preferredConfig = PatientUtil.loadConfigItem(this, pageConfigKey);
//        return new MainPageConfigImpl(preferredConfig);
//    }

    @Override
    public void addSummaryTab() {
        initHomePageTab(SummaryActivity.class);
    }

    @Override
    public void addRecyclerSummaryTab() {
        initHomePageTab(RecyclerSummaryActivity.class);
    }

    @Override
    public void addRecyclerSummaryV2Tab() {
        initHomePageTab(RecyclerSummaryV2Activity.class);
    }

    @Override
    public void addUnknownSummaryTab() {
        // do nothing to prevent adding any HOME tab
        hideTabPanel(summaryBadgeRadio);
    }

    private void initHomePageTab(@NonNull Class<?> cls) {
        TabSpec tabNearby = tabHost.newTabSpec(TAB_SPEC_SUMMARY);
        tabNearby.setIndicator(TAB_SPEC_SUMMARY).setContent(new Intent(this, cls));
        initTabPanel(tabNearby, summaryBadgeRadio, R.string.radio_label_summary, R.drawable.radio_bg_summary, listener);
    }

//    private boolean initTabPanel(TabSpec tabSpec, View panel, View tabView, RadioClickListener listener) {
//        if (null == tabSpec) {
//            panel.setVisibility(View.GONE);
//            return false;
//        } else {
//            tabHost.addTab(tabSpec);
//            tabView.setOnClickListener(listener);
//            panel.setVisibility(View.VISIBLE);
//        }
//        return true;
//    }

    private boolean initTabPanel(TabSpec tabSpec, BadgeRadioButton panel, int labelId, int iconId,
                                 RadioClickListener listener) {
//        if (null == tabSpec) {
//            panel.setVisibility(View.GONE);
//            return false;
//        } else {
            tabHost.addTab(tabSpec);
            panel.setOnClickListener(labelId, iconId, listener);
            panel.setVisibility(View.VISIBLE);
//        }
        return true;
    }

    private void hideTabPanel(@NonNull BadgeRadioButton panel) {
        panel.setVisibility(View.GONE);
    }

    @Override
    public void addMessageTab() {
        tabConversation = tabHost.newTabSpec(TAB_SPEC_CONVERSATION);
        initOrUpdateConversationTab();
        initTabPanel(tabConversation, conversationBadgeRadio, R.string.radio_label_conversation, R.drawable.radio_bg_conversation, listener);
    }

    @Override
    public void addUnknownMessageTab() {
        // do nothing to prevent adding any MESSAGE tab
        hideTabPanel(conversationBadgeRadio);
    }

    @Override
    public void addDoctorTab() {
        TabSpec tabDoctor = tabHost.newTabSpec(TAB_SPEC_CONTACT);
        tabDoctor.setIndicator(TAB_SPEC_CONTACT).setContent(
                new Intent(this, TabSearchDoctorActivity.class));
        initTabPanel(tabDoctor, contactBadgeRadio, R.string.radio_label_contact, R.drawable.radio_bg_explore, listener);
    }

    @Override
    public void addUnknownDoctorTab() {
        // do nothing to prevent adding any DOCTOR tab
        hideTabPanel(contactBadgeRadio);
    }

    @Override
    public void addMineTab() {
        TabSpec tabMine = tabHost.newTabSpec(TAB_SPEC_PROFILE);
        tabMine.setIndicator(TAB_SPEC_PROFILE).setContent(new Intent(this, MineActivity.class));
        initTabPanel(tabMine, profileBadgeRadio, R.string.radio_label_profile, R.drawable.radio_bg_profile, listener);
    }

    @Override
    public void addUnknownMineTab() {
        // do nothing to prevent adding any MINE tab
        hideTabPanel(profileBadgeRadio);
    }

    @Override
    public void addDiscoveryTab() {
        TabSpec tabDiscovery = tabHost.newTabSpec(TAB_SPEC_EXPLORE);
        tabDiscovery.setIndicator(TAB_SPEC_EXPLORE).setContent(
                new Intent(this, DiscoveryActivity.class));
        initTabPanel(tabDiscovery, exploreBadgeRadio, R.string.radio_label_explore, R.drawable.radio_bg_explore, listener);
    }

    @Override
    public void addUnknownDiscoveryTab() {
        // do nothing to prevent adding any DISCOVERY tab
        hideTabPanel(exploreBadgeRadio);
    }

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
        toastWrapper.cancel();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (KeyEventHelper.isBackKeyDown(event)) {
            if (exitingTrigger.testExpired(System.currentTimeMillis())) {
                toastWrapper.show(this);
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
        //bindUnreadCountTextView(view, R.id.badge);
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
