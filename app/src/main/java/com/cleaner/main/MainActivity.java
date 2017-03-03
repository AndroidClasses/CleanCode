package com.cleaner.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.cleaner.CleanCodeApplication;
import com.cleaner.main.fragments.ChatListFragment;
import com.cleaner.main.fragments.ContactListFragment;
import com.cleaner.main.fragments.ExploreFragment;
import com.cleaner.main.fragments.ProfileFragment;
import com.cleaner.main.fragments.RecyclerSummaryFragment;
import com.cleaner.main.fragments.RecyclerSummaryV2Fragment;
import com.cleaner.main.fragments.SummaryFragment;
import com.cleaner.view.BadgeRadioButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainConfigContracts.ConfigView, View.OnClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();

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

    private BadgeRadioButton currentButton;
    private boolean inited = false;

    private static final int EVENT_SUMMARY = 0;
    private static final int EVENT_CONVERSATION = 1;
    private static final int EVENT_CONTACT = 4;
    private static final int EVENT_EXPLORE = 5;
    private static final int EVENT_PROFILE = 3;

    private final ExitingTrigger exitingTrigger = new ExitingTrigger();
    private final ToastWrapper toastWrapper = new ToastWrapper(R.string.toast_exit, Toast.LENGTH_LONG);

    private Unbinder unbinder;


    @Inject MainConfigPresenterImpl configPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        configPresenter.initTabs();

        initContents();
        initBottomTabs();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        parseNewIntent(intent);

        initContents();
        initBottomTabs();
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

    @Override
    public void addSummaryTab() {
        replaceContentTab(inited ? SummaryFragment.newInstance() : SummaryFragment.newInstance(getIntent().getExtras()));
//        initHomePageTab(SummaryActivity.class);
    }

    @Override
    public void addRecyclerSummaryTab() {
        replaceContentTab(inited ? RecyclerSummaryFragment.newInstance() : RecyclerSummaryFragment.newInstance(getIntent().getExtras()));
//        initHomePageTab(RecyclerSummaryActivity.class);
    }

    @Override
    public void addRecyclerSummaryV2Tab() {
        replaceContentTab(inited ? RecyclerSummaryV2Fragment.newInstance() : RecyclerSummaryV2Fragment.newInstance(getIntent().getExtras()));
//        initHomePageTab(RecyclerSummaryV2Activity.class);
    }

    @Override
    public void addUnknownSummaryTab() {
        // do nothing to prevent adding any HOME tab
        hideTabPanel(summaryBadgeRadio);
    }

    @Override
    public void showSummaryTab() {
        initTabPanel(summaryBadgeRadio, R.string.radio_label_summary, R.drawable.radio_bg_summary);
    }

    private boolean initTabPanel(BadgeRadioButton panel, int labelId, int iconId) {
        panel.setOnClickListener(labelId, iconId, this);
        panel.setVisibility(View.VISIBLE);
        return true;
    }

    private void hideTabPanel(@NonNull BadgeRadioButton panel) {
        panel.setVisibility(View.GONE);
    }

    @Override
    public void addMessageTab() {
        initTabPanel(conversationBadgeRadio, R.string.radio_label_conversation, R.drawable.radio_bg_conversation);
    }

    @Override
    public void addUnknownMessageTab() {
        // do nothing to prevent adding any MESSAGE tab
        hideTabPanel(conversationBadgeRadio);
    }

    @Override
    public void addDoctorTab() {
        initTabPanel(contactBadgeRadio, R.string.radio_label_contact, R.drawable.radio_bg_explore);
    }

    @Override
    public void addUnknownDoctorTab() {
        // do nothing to prevent adding any DOCTOR tab
        hideTabPanel(contactBadgeRadio);
    }

    @Override
    public void addMineTab() {
        initTabPanel(profileBadgeRadio, R.string.radio_label_profile, R.drawable.radio_bg_profile);
    }

    @Override
    public void addUnknownMineTab() {
        // do nothing to prevent adding any MINE tab
        hideTabPanel(profileBadgeRadio);
    }

    @Override
    public void addDiscoveryTab() {
        initTabPanel(exploreBadgeRadio, R.string.radio_label_explore, R.drawable.radio_bg_explore);
    }

    @Override
    public void addUnknownDiscoveryTab() {
        // do nothing to prevent adding any DISCOVERY tab
        hideTabPanel(exploreBadgeRadio);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        contactBadgeRadio.performClick();
    }

    // dumb override methods
    protected void parseNewIntent(Intent intent) {

    }
    protected void reportTabClickEvent(int tabIndex) {

    }
    protected void bindUnreadCountTextView(BadgeRadioButton view) {
        //bindUnreadCountTextView(view, R.id.badge);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == currentButton.getId()) {
            return;
        }

        currentButton.setSelectFlag(false);

        switch (v.getId()) {
            case R.id.summaryBadgeRadio:
                activateSummary();
                reportTabClickEvent(EVENT_SUMMARY);
                break;
            case R.id.conversationBadgeRadio:
                activateMessage();
                reportTabClickEvent(EVENT_CONVERSATION);
                break;
            case R.id.contactBadgeRadio:
                activateContactTab();
                reportTabClickEvent(EVENT_CONTACT);
                break;
            case R.id.exploreBadgeRadio:
                activateExploreTab();
                reportTabClickEvent(EVENT_EXPLORE);
                break;
            case R.id.profileBadgeRadio:
                activateMineTab();
                reportTabClickEvent(EVENT_PROFILE);
                break;
            default:
                showUnknownTabError();
                return;
        }

        currentButton = (BadgeRadioButton) v;
    }

    private void initContents() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.tabcontent);
        if (fragment == null) {
            configPresenter.getSummaryFragment();
            inited = true;
        } else {
            attachContentTab(fragment);
        }
    }

    private void initBottomTabs() {
//        root.check(R.id.summaryBadgeRadio);
//        root.setOnCheckedChangeListener(this);
        currentButton = summaryBadgeRadio;
        currentButton.performClick();
    }

    private void activateSummary() {
        configPresenter.getSummaryFragment();
    }

    private void activateMessage() {
        replaceContentTab(ChatListFragment.newInstance());
    }

    private void activateContactTab() {
        replaceContentTab(ContactListFragment.newInstance());
    }

    private void activateExploreTab() {
        replaceContentTab(ExploreFragment.newInstance());
    }

    private void activateMineTab() {
        replaceContentTab(ProfileFragment.newInstance());
    }

    private void attachContentTab(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .attach(fragment)
                .commit();
    }
    private void replaceContentTab(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(android.R.id.tabcontent, fragment, "")
                .commit();
    }

    private void showUnknownTabError() {
    }
}
