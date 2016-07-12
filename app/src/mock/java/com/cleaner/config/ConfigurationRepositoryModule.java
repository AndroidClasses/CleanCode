package com.cleaner.config;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfigurationRepositoryModule {
    @Singleton
    @Provides
    PageConfig provideMainPageConfig(Context context) {
        return new FakeMainPageConfigImpl();
    }
}
