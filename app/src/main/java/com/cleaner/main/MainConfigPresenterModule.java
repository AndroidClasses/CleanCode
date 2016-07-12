package com.cleaner.main;

import dagger.Module;
import dagger.Provides;


@Module
public class MainConfigPresenterModule {

    private final MainConfigContracts.ConfigView mView;

    public MainConfigPresenterModule(MainConfigContracts.ConfigView view) {
        mView = view;
    }

    @Provides
    MainConfigContracts.ConfigView provideConfigContractView() {
        return mView;
    }

}
