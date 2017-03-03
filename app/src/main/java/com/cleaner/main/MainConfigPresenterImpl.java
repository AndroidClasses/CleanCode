package com.cleaner.main;

import com.cleaner.config.ConfigurationRepository;
import com.cleaner.config.PageConfig;

import javax.inject.Inject;

/**
 * Created by yangfeng on 16-6-29.
 */
public class MainConfigPresenterImpl implements MainConfigContracts.ConfigPresenter {
    private final MainConfigContracts.ConfigView view;
    private final PageConfig pageConfig;

    @Inject
    public MainConfigPresenterImpl(ConfigurationRepository config, MainConfigContracts.ConfigView view) {
        this.view = view;
        this.pageConfig = config;
    }

    @Override
    public void initTabs() {
        initHomePageTab();
        initMessagePageTab();
        initDoctorPageTab();
        initMinePageTab();
        initDiscoveryPageTab();
    }

    private void initHomePageTab() {
        if (pageConfig.hasSummary()) {
            view.showSummaryTab();
        } else if (pageConfig.hasRecyclerSummary()) {
            view.showSummaryTab();
        } else if (pageConfig.hasRecyclerSummaryV2()) {
            view.showSummaryTab();
        } else {
            view.addUnknownSummaryTab();
        }
    }

    @Override
    public void getSummaryFragment() {
        if (pageConfig.hasSummary()) {
            view.addSummaryTab();
        } else if (pageConfig.hasRecyclerSummary()) {
            view.addRecyclerSummaryTab();
        } else if (pageConfig.hasRecyclerSummaryV2()) {
            view.addRecyclerSummaryV2Tab();
        } else {
            view.addSummaryTab();
        }
    }

    private void initMessagePageTab() {
        if (pageConfig.hasMessage()) {
            view.addMessageTab();
        } else {
            view.addUnknownMessageTab();
        }
    }

    private void initDoctorPageTab() {
        if (pageConfig.hasDoctorPage()) {
            view.addDoctorTab();
        } else {
            view.addUnknownDoctorTab();
        }
    }

    private void initMinePageTab() {
        if (pageConfig.hasMinePage()) {
            view.addMineTab();
        } else {
            view.addUnknownMineTab();
        }
    }

    private void initDiscoveryPageTab() {
        if (pageConfig.hasDiscoveryPage()) {
            view.addDiscoveryTab();
        } else {
            view.addUnknownDiscoveryTab();
        }
    }
}
