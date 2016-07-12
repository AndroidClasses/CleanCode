package com.cleaner.config;

/**
 * Created by yangfeng on 16-6-29.
 */
public class FakeMainPageConfigImpl implements PageConfig {
    private int summaryVersion = VERSION_FIRST;
    private int conversationVersion = VERSION_NONE;
    private int contactVersion = VERSION_NONE;
    private int exploreVersion = VERSION_NONE;
    private int profileVersion = VERSION_NONE;

    public FakeMainPageConfigImpl() {
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
