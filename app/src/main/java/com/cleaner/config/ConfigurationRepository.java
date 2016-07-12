
package com.cleaner.config;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConfigurationRepository implements PageConfig {

    private final PageConfig pageConfig;

    @Inject
    ConfigurationRepository(/*@Remote*/ PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    @Override
    public boolean hasSummary() {
        return pageConfig.hasSummary();
    }

    @Override
    public boolean hasRecyclerSummary() {
        return pageConfig.hasRecyclerSummary();
    }

    @Override
    public boolean hasRecyclerSummaryV2() {
        return pageConfig.hasRecyclerSummaryV2();
    }

    @Override
    public boolean hasMessage() {
        return pageConfig.hasMessage();
    }

    @Override
    public boolean hasDoctorPage() {
        return pageConfig.hasDoctorPage();
    }

    @Override
    public boolean hasMinePage() {
        return pageConfig.hasMinePage();
    }

    @Override
    public boolean hasDiscoveryPage() {
        return pageConfig.hasDiscoveryPage();
    }
}
