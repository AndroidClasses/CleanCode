package com.cleaner.config;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yangfeng on 16-6-29.
 */
public class MainPageConfigImpl implements PageConfig {
    private int summaryVersion = VERSION_NONE;
    private int conversationVersion = VERSION_NONE;
    private int contactVersion = VERSION_NONE;
    private int exploreVersion = VERSION_NONE;
    private int profileVersion = VERSION_NONE;

    public static String getPageConfigKey() {
        return BaseConstantDef.CONFIG_KEY_MAIN_ACTIVITY;
    }

    public MainPageConfigImpl(String config) {
        if (!TextUtils.isEmpty(config)) {
            List<String> mActivityConfigs = Arrays.asList(config.replaceAll(" ", "").split(","));
            if (BaseConstantDef.hasSummary(mActivityConfigs)) {
                summaryVersion = VERSION_FIRST;
            } else if (BaseConstantDef.hasRecyclerSummary(mActivityConfigs)) {
                summaryVersion = VERSION_SECOND;
            } else if (BaseConstantDef.hasRecyclerSummaryV2(mActivityConfigs)) {
                summaryVersion = VERSION_THIRD;
            } else {
                // left it as default none version
            }

            if (BaseConstantDef.hasMessage(mActivityConfigs)) {
                conversationVersion = VERSION_FIRST;
            }

            if (BaseConstantDef.hasDoctorPage(mActivityConfigs)) {
                contactVersion = VERSION_FIRST;
            }

            if (BaseConstantDef.hasMinePage(mActivityConfigs)) {
                profileVersion = VERSION_FIRST;
            }

            if (BaseConstantDef.hasDiscoveryPage(mActivityConfigs)) {
                exploreVersion = VERSION_FIRST;
            }
        }
    }

    public boolean hasSummary() {
        return summaryVersion == VERSION_FIRST;
    }

    public boolean hasRecyclerSummary() {
        return summaryVersion == VERSION_SECOND;
    }

    public boolean hasRecyclerSummaryV2() {
        return summaryVersion == VERSION_THIRD;
    }

    public boolean hasMessage() {
        return conversationVersion != VERSION_NONE;
    }

    public boolean hasDoctorPage() {
        return contactVersion != VERSION_NONE;
    }

    public boolean hasMinePage() {
        return profileVersion != VERSION_NONE;
    }

    public boolean hasDiscoveryPage() {
        return exploreVersion != VERSION_NONE;
    }
}
