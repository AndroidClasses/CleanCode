package com.cleaner.main;

import com.cleaner.config.ConfigurationRepositoryComponent;
import com.cleaner.util.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = ConfigurationRepositoryComponent.class, modules = MainConfigPresenterModule.class)
public interface MainConfigComponent {

    void inject(MainActivity activity);
}
