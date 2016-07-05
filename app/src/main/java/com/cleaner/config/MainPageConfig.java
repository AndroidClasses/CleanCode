package com.cleaner.config;

import android.content.Context;

import java.util.List;

/**
 * Created by yangfeng on 16-6-27.
 */
public class MainPageConfig {
    private final List<String> mActivityConfigs;

    private String getPageConfigKey() {
        return BaseConstantDef.CONFIG_KEY_MAIN_ACTIVITY;
    }

    public MainPageConfig(Context context) {
        mActivityConfigs = PatientUtil.getActivityConfigs(context, getPageConfigKey());
    }

    public boolean hasSummary() {
        return BaseConstantDef.hasSummary(mActivityConfigs);
    }

    public boolean hasRecyclerSummary() {
        return BaseConstantDef.hasRecyclerSummary(mActivityConfigs);
    }

    public boolean hasRecyclerSummaryV2() {
        return BaseConstantDef.hasRecyclerSummaryV2(mActivityConfigs);
    }

    public boolean hasMessage() {
        return BaseConstantDef.hasMessage(mActivityConfigs);
    }

    public boolean hasDoctorPage() {
        return BaseConstantDef.hasDoctorPage(mActivityConfigs);
    }

    public boolean hasMinePage() {
        return BaseConstantDef.hasMinePage(mActivityConfigs);
    }

    public boolean hasDiscoveryPage() {
        return BaseConstantDef.hasDiscoveryPage(mActivityConfigs);
    }
}
