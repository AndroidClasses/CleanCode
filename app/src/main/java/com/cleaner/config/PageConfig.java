package com.cleaner.config;

/**
 * Created by yangfeng on 16-6-27.
 */
public interface PageConfig {
    int VERSION_NONE = 0;
    int VERSION_FIRST = 1;
    int VERSION_SECOND = 2;
    int VERSION_THIRD = 3;

    boolean hasSummary();
    boolean hasRecyclerSummary();
    boolean hasRecyclerSummaryV2();

    boolean hasMessage();

    boolean hasDoctorPage();

    boolean hasMinePage();

    boolean hasDiscoveryPage();
}
