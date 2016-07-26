package com.cleaner;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cleaner.config.ConfigurationRepositoryComponent;
import com.cleaner.config.DaggerConfigurationRepositoryComponent;
import com.cleaner.config.ConfigurationRepositoryModule;

public class CleanCodeApplication extends Application {

    private ConfigurationRepositoryComponent mRepositoryComponent;

    // Prevent need in a singleton (global) reference to the application object.
    @NonNull
    public static CleanCodeApplication get(@NonNull Context context) {
        return (CleanCodeApplication) context.getApplicationContext();
    }

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
