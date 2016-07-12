package com.cleaner;

import android.app.Application;

import com.cleaner.config.ConfigurationRepositoryComponent;
import com.cleaner.config.DaggerConfigurationRepositoryComponent;
import com.cleaner.config.ConfigurationRepositoryModule;

public class CleanCodeApplication extends Application {

    private ConfigurationRepositoryComponent mRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mRepositoryComponent = DaggerConfigurationRepositoryComponent.builder()
                .applicationModule(new ApplicationModule((getApplicationContext())))
                .configurationRepositoryModule(new ConfigurationRepositoryModule()).build();

    }

    public ConfigurationRepositoryComponent getConfigurationRepositoryComponent() {
        return mRepositoryComponent;
    }

}
