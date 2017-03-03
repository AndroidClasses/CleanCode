package com.cleaner.main;

/**
 * Created by yangfeng on 16-6-29.
 */
public class MainConfigContracts {

    /**
     * Created by yangfeng on 16-6-29.
     */
    public interface ConfigView {
        void showSummaryTab();

        void addSummaryTab();
        void addRecyclerSummaryTab();
        void addRecyclerSummaryV2Tab();

        void addUnknownSummaryTab();
        void addMessageTab();

        void addUnknownMessageTab();
        void addDoctorTab();

        void addUnknownDoctorTab();
        void addMineTab();

        void addUnknownMineTab();
        void addDiscoveryTab();

        void addUnknownDiscoveryTab();
    }

    /**
     * Created by yangfeng on 16-6-29.
     */
    public interface ConfigPresenter {
        void initTabs();
        void getSummaryFragment();
    }
}
